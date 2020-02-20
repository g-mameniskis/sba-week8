package com.github.perscholas.model;

public class StudentRegistration implements StudentRegistrationInterface {

    private String email;
    private Integer id;

    public StudentRegistration(String email, Integer id) {
        this.email = email;
        this.id = id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
