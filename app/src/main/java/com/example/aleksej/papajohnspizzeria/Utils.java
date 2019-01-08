package com.example.aleksej.papajohnspizzeria;

import java.text.ParseException;
import java.util.List;

public class Utils {

    static List<User> users;

    public static boolean checkLogIn(String username, String password) throws ParseException {
        users = UsersRepo.getAllUsers();

        for(User u : users){
            System.out.println(u.getUsername());
            System.out.println(u.getPassword());
            if (u.getUsername() == username && u.getPassword() == password) {
                return true;
            }
        }

        return false;
    }


}
