package com.example.demo.controller;

public class IdGenerator {
    private static Integer id = 0;

    public static void increment() {
        id++;
    }

    public static Integer getId() {
        return id;
    }
}
