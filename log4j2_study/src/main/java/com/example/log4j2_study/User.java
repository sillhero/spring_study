package com.example.log4j2_study;


public class User {

    private String name;

    private Person person;

    public void add() {
        System.out.println("add....");
    }

    User() {
        System.out.println("User");
    }

    public static void main(String[] args) {
        User user = new User();
        user.add();
    }
}
