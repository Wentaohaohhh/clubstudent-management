package com.example.clubstudentmanagement.exceptions;

public class StudentEmptyNameException extends RuntimeException{

    public StudentEmptyNameException(String message) {
        super(message);
    }
}
