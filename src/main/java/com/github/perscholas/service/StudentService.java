package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO - Implement respective DAO interface
public class StudentService implements StudentDao {

    private final DatabaseConnection dbc;

    public StudentService(DatabaseConnection dbc) {
        this.dbc = dbc;
    }

    public StudentService() {
        this(DatabaseConnection.MARIADB);
    }

    public List<StudentInterface> getAllStudentsWhere(String tableName, String condition) throws SQLException {
        ResultSet result = dbc.executeQuery("SELECT * FROM " + tableName.toLowerCase() + " WHERE " + condition + ";");
        List<StudentInterface> list = new ArrayList<>();
        try {
            while (result.next()) {
                String studentEmail = result.getString("email");
                String name = result.getString("name");
                String password = result.getString("password");
                StudentInterface student = new Student(studentEmail, name, password);
                list.add(student);
            }
        } catch(SQLException se) {
            throw new Error(se);
        }

        return list;
    }

    @Override
    public List<StudentInterface> getAllStudents() throws SQLException {
        return getAllStudentsWhere("student", "true");
    }


    @Override
    public StudentInterface getStudentByEmail(String studentEmail) throws SQLException {
        return getAllStudentsWhere("student", "`email` = '" + studentEmail + "'").get(0);
    }

    @Override
    public Boolean validateStudent(String studentEmail, String studentPassword) throws SQLException {
        return getStudentByEmail(studentEmail).getPassword().equals(studentPassword);
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) throws SQLException {
        List registeredStudents = getAllStudentsWhere(
                        "student_registration",
                        "email=" + "'" + studentEmail.toLowerCase() + "'" + " AND id=" + "'" + courseId + "'");

        if (registeredStudents.isEmpty()) {
            dbc.executeQuery("INSERT INTO student_registration (email, id) values" +
                    " ('" + studentEmail + "', '" + courseId + "')");
        }
    }

//    @Override
//    public List<CourseInterface> getStudentCourses(String studentEmail) throws SQLException {
//
//        ResultSet result = dbc.executeQuery("SELECT * FROM " + tableName.toLowerCase() + " WHERE " + condition + ";");
//        List<CourseInterface> list = new ArrayList<>();
//        try {
//            while (result.next()) {
//                Integer id = result.getInt("id");
//                String name = result.getString("name");
//                String instructor = result.getString("instructor");
//                CourseInterface course = new Course(id, name, instructor);
//                list.add(course);
//            }
//        } catch(SQLException se) {
//            throw new Error(se);
//        }
//
//        return list;
//
//
//
//
//        List studentInformationObject = getAllStudentsWhere("student_registration", "email= " + "'" + studentEmail + "'");
//
//        Object student = studentInformationObject.get(0);
//
//
//        List studentCourseList = getAllStudentsWhere("student_registration s, courses c", " s.email=" + "'c." + studentEmail + "'");
//
//
//
//        List<CourseInterface> courseInformation = studentCourseList;
//    }
}





















//package com.github.perscholas.service;
//
//import com.github.perscholas.DatabaseConnection;
//import com.github.perscholas.dao.StudentDao;
//import com.github.perscholas.model.CourseInterface;
//import com.github.perscholas.model.Student;
//import com.github.perscholas.model.StudentInterface;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//// TODO - Implement respective DAO interface
//public class StudentService implements StudentDao {
//
//    private final DatabaseConnection dbc;
//
//    public StudentService(DatabaseConnection dbc) {
//        this.dbc = dbc;
//    }
//
//    public StudentService() {
//        this(DatabaseConnection.MARIADB);
//    }
//
//    @Override
//    public List<StudentInterface> getAllStudents() throws SQLException {
//        ResultSet result = dbc.executeQuery("SELECT * FROM student");
//        List<StudentInterface> list = new ArrayList<>();
//        try {
//            while (result.next()) {
//                String studentEmail = result.getString("email");
//                String name = result.getString("name");
//                String password = result.getString("password");
//                StudentInterface student = new Student(studentEmail, name, password);
//                list.add(student);
//            }
//        } catch(SQLException se) {
//            throw new Error(se);
//        }
//
//        return list;
//    }
//
//    @Override
//    public StudentInterface getStudentByEmail(String studentEmail) throws SQLException {
//        ResultSet result = dbc.executeQuery("SELECT * FROM student WHERE email=" + "'" + studentEmail.toLowerCase() + "'");
//        List<StudentInterface> list = new ArrayList<>();
//        try {
//            while (result.next()) {
//                String email = result.getString("email");
//                String name = result.getString("name");
//                String password = result.getString("password");
//                StudentInterface student = new Student(email, name, password);
//                list.add(student);
//            }
//        } catch(SQLException se) {
//            throw new Error(se);
//        }
//        return list.get(0);
//    }
//
//    @Override
//    public Boolean validateStudent(String studentEmail, String studentPassword) throws SQLException {
//        ResultSet result = dbc.executeQuery(
//                "SELECT * FROM student WHERE email="+ "'" + studentEmail.toLowerCase() + "'" +
//                        " AND PASSWORD=" + "'" + studentPassword.toLowerCase() + "'");
//        List<StudentInterface> list = new ArrayList<>();
//        try {
//            while (result.next()) {
//                String email = result.getString("email");
//                String name = result.getString("name");
//                String password = result.getString("password");
//                StudentInterface student = new Student(email, name, password);
//                list.add(student);
//            }
//        } catch(SQLException se) {
//            throw new Error(se);
//        }
//
//        return !list.isEmpty();
//    }
//
//    @Override
//    public void registerStudentToCourse(String studentEmail, int courseId) throws SQLException {
//        ResultSet result = dbc.executeQuery(
//                "SELECT * FROM student_registration WHERE email="+ "'" + studentEmail.toLowerCase() + "'" +
//                        " AND id=" + "'" + courseId + "'");
//        List<StudentInterface> list = new ArrayList<>();
//        try {
//            while (result.next()) {
//                String email = result.getString("email");
//                String name = result.getString("name");
//                String password = result.getString("password");
//                StudentInterface registeredStudent = new Student(email, name, password);
//                list.add(registeredStudent);
//            }
//        } catch(SQLException se) {
//            throw new Error(se);
//        }
//        if (list.isEmpty()) {
//            dbc.executeQuery("insert into student_registration (email, id) values" +
//                    " ('" + studentEmail + "', '" + courseId + "')");
//        }
//    }
//
//    @Override
//    public List<CourseInterface> getStudentCourses(String studentEmail) {
//        return null;
//    }
//}
