package com.ileiwe.web.exceptions;

public class CourseAlreadyExistException extends RuntimeException {
    public CourseAlreadyExistException(String s) {
        super(s);
    }
}
