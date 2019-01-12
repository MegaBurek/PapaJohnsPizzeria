package com.example.aleksej.papajohnspizzeria;

public class User {

    public static final String TABLE_NAME="users";
    public static final String FIELD_USER_ID="userID";
    public static final String FIELD_NAME="name";
    public static final String FIELD_SURNAME="surname";
    public static final String FIELD_USERNAME="username";
    public static final String FIELD_PASSWORD="password";
    public static final String FIELD_RESERVE="reserves";

    private String name,surname, username, password;
    private int userID,reserves;

    public User(int userID,String name, String surname, String username, String password, int reserves){
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.reserves = reserves;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public Integer getReserves() {
        return reserves;
    }

    public void setReserves(Integer reserves) {
        this.reserves = reserves;
    }
}
