package com.employeeservices.exceptions;
public class EmployeeNotFoundException extends Exception{
    public  EmployeeNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
