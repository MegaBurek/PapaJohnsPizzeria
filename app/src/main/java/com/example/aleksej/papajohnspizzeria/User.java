package com.example.aleksej.papajohnspizzeria;

public class User {

    public static final String TABLE_NAME="users";
    public static final String FIELD_NAME="name";
    public static final String FIELD_SURNAME="surname";
    public static final String FIELD_USERNAME="username";
    public static final String FIELD_PASSWORD="password";
    public static final String FIELD_RESERVE="Users";

    private String name,surname, username, password;
    private int Users;

    public User(String name, String surname, String username, String password, int Users){
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.Users = Users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUsers() {
        return Users;
    }

    public void setUsers(Integer Users) {
        this.Users = Users;
    }
}
