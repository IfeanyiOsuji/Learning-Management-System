package com.ileiwe.web;

public class CourseAlreadyExistException extends RuntimeException {
    public CourseAlreadyExistException(String s) {
        super(s);
    }
}
