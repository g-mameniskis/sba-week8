package com.github.perscholas.dao;

import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.StudentRegistrationInterface;

import java.sql.SQLException;
import java.util.List;

public interface StudentRegistrationDao {

    List<StudentRegistrationInterface> getStudentCourses(String studentEmail) throws SQLException;
}
