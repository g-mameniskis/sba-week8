package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.dao.StudentRegistrationDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.StudentRegistration;
import com.github.perscholas.model.StudentRegistrationInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRegistrationService implements StudentRegistrationDao {
    private DatabaseConnection dbc;

    public StudentRegistrationService(DatabaseConnection dbc) {
        this.dbc = dbc;
    }

    @Override
    public List<StudentRegistrationInterface> getStudentCourses(String studentEmail) throws SQLException {

        ResultSet result = dbc.executeQuery("SELECT * FROM student_registration WHERE `email` = '" + studentEmail +  "'");
        List<StudentRegistrationInterface> list = new ArrayList<>();
        try {
            while (result.next()) {
                String email = result.getString("email");
                Integer id = result.getInt("id");
                StudentRegistrationInterface student = new StudentRegistration(email, id);
                list.add(student);
            }
        } catch(SQLException se) {
            throw new Error(se);
        }

        return list;




//        List<CourseInterface> list = new ArrayList<>();
//        String sqlStatement = "SELECT * FROM student_registration WHERE `email` = '" + studentEmail +  "'";
//        CourseDao courseService = new CourseService();
//        try {
//            ResultSet studentAndEmailList = dbc.executeQuery(sqlStatement); // get all registered students
//            while(studentAndEmailList.next()) { // iterate through resulting "list"
//                int id = studentAndEmailList.getInt("id"); // get course id from current student
//                for (CourseInterface course : courseService.getAllCourses()) { // get the course from the course id
//                    if (course.getId() == id) {
//                        list.add(course); // add course to lists
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            throw new Error(e);
//        }
//        return list;
    }
}
