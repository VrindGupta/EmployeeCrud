package com.vrind.employee.exception;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String status) {
        super(status);
    }
}
