package com.github.perscholas.service;

import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.CourseInterface;

import java.util.ArrayList;
import java.util.List;

// TODO - Implement respective DAO interface
public class CourseService implements CourseDao {
    @Override
    public List<CourseInterface> getAllCourses() {
        return new ArrayList<>(); // TODO - replace this ArrayList with acutal Implementation
    }
}
