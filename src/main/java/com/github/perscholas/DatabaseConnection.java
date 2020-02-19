
package com.github.perscholas;

import javax.xml.crypto.Data;
import java.sql.*;

/**
 * Created by leon on 2/18/2020.
 */
public enum DatabaseConnection {
    MARIADB;

    public Connection getConnection() {
        return getConnection(name().toLowerCase());
    }

    public Connection getConnection(String dbVendor) {
        String username = "root";
        String password = "";
        String databaseName = "SBA_week8";
        String url = "jdbc:" + dbVendor + "://127.0.0.1/";
        try {
            Connection connection = DriverManager.getConnection(url + databaseName, username, password);
            return connection;
        } catch (SQLException e) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                return connection;
            } catch (SQLException err) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public ResultSet executeQuery(String sqlStatement) throws SQLException {
        Connection connection = DatabaseConnection.MARIADB.getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(sqlStatement);
    }

    public void executeStatement(String sqlStatement) {
        try {
            Statement statement = getScrollableStatement();
            statement.execute(sqlStatement);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    private Statement getScrollableStatement() {
        int resultSetType = ResultSet.TYPE_SCROLL_INSENSITIVE;
        int resultSetConcurrency = ResultSet.CONCUR_READ_ONLY;
        try {
            return getConnection().createStatement(resultSetType, resultSetConcurrency);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }
}