package com.employeeservices.exceptions;

public class DuplicateValueException extends  Exception{
    public DuplicateValueException(String errorMessage){
        super(errorMessage);
    }
}
