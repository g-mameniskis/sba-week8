package com.github.perscholas;

import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.dao.StudentRegistrationDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentRegistrationInterface;
import com.github.perscholas.service.CourseService;
import com.github.perscholas.service.StudentRegistrationService;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.IOConsole;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchoolManagementSystem implements Runnable {
    private static final IOConsole console = new IOConsole();

//    @Lombok SneakyThrows
    @Override
    public void run() {
        String smsDashboardInput = getSchoolManagementSystemDashboardInput();
        if ("login".equals(smsDashboardInput)) {
            StudentDao studentService = new StudentService(DatabaseConnection.MARIADB);
            String studentEmail = console.getStringInput("Enter your email:");
            System.out.println("Email: " + studentEmail);
            String studentPassword = console.getStringInput("Enter your password:");
            System.out.println("Password: " + studentPassword);

            try {
                System.out.println(studentService.getAllStudents().toString());
                System.out.println("Students printed");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            Boolean isValidLogin = null;
            try {
                isValidLogin = studentService.validateStudent(studentEmail, studentPassword);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (isValidLogin) {
                String studentDashboardInput = getStudentDashboardInput();
                if ("register".equals(studentDashboardInput)) {
                    Integer courseId = null;
                    try {
                        courseId = getCourseRegistryInput();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        studentService.registerStudentToCourse(studentEmail, courseId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("HERE IS MY TEST");

                try {
                    if ("1".equals(getCourseRegistryInput()))
                     try {
                         Integer studentInteger = getCourseRegistryInput();
                     } catch (SQLException e) {
                         e.printStackTrace();
                     }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    if ("view".equals(studentDashboardInput)) {
                        StudentRegistrationDao student = new StudentRegistrationService(DatabaseConnection.MARIADB);
                        System.out.println("TEST");
                        try {
                            List<StudentRegistrationInterface> courses = student.getStudentCourses(studentEmail);
                            System.out.println("Your Courses");
                            courses.forEach(System.out::println);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getSchoolManagementSystemDashboardInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the School Management System Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ login ], [ logout ]")
                .toString());
    }

    private String getStudentDashboardInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Student Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ register ], [ logout]")
                .toString());
    }

    private Integer getCourseRegistryInput() throws SQLException {
        List<Integer> listOfCoursesIds = new ArrayList<>();
        CourseDao courseService = new CourseService(DatabaseConnection.MARIADB);
        List<CourseInterface> courses = courseService.getAllCourses();
        for(CourseInterface course : courses) {
            listOfCoursesIds.add(course.getId());
        }
        return console.getIntegerInput(new StringBuilder()
                .append("Welcome to the Course Registration Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                // names of courses
                .append("\n\t")
                .append("\n\t" + listOfCoursesIds.toString())
                .toString());
    }

    private String getViewInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Student Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ view ], [ logout ]")
                .toString());
    }
}
