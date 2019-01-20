package com.example.aleksej.papajohnspizzeria;

import java.util.ArrayList;

public class UserAPI {

    public static ArrayList<User> getUsers(){

        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1,"Mark","Jones","mk1","jk2",5));
        users.add(new User(2,"Sam","White","sk1","wk2",2));
        users.add(new User(3,"John","Doe","jd1","dk2",5));

        return users;
    }

    public static boolean checkLogIn(String username, String password){

        ArrayList<User> users = getUsers();

        for(User u : users){
            if(u.getUsername() == username && u.getPassword() == password){
                return true;
            }
        }
        return false;

    }
}
