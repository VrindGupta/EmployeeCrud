package com.vrind.employee.exception;

public class EmployeeNotSavedException extends RuntimeException {
    public EmployeeNotSavedException(String status) {
        super(status);
    }
}
