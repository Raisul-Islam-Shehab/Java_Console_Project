package com.Logicaly;

public class InvalidEmployeeDetailException extends RuntimeException {

    InvalidEmployeeDetailException() {
        super("Invalid Input for employee");
    }

    InvalidEmployeeDetailException(String message) {
        super(message);
    }

}
