package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO - Implement respective DAO interface
public class CourseService implements CourseDao {

    private final DatabaseConnection dbc;

    public CourseService(DatabaseConnection dbc) {
        this.dbc = dbc;
    }

    @Override
    public List<CourseInterface> getAllCourses() throws SQLException {

        ResultSet result = dbc.executeQuery("SELECT * FROM course");
        List<CourseInterface> list = new ArrayList<>();

        try {
            while (result.next()) {
                Integer courseId = result.getInt("id");
                String courseName = result.getString("name");
                String courseInstructor = result.getString("instructor");
                CourseInterface course = new Course(courseId, courseName, courseInstructor);
                list.add(course);
            }
        } catch (SQLException se) {
            throw new Error(se);
        }
            return list; // TODO - replace this ArrayList with acutal Implementation

    }
}
