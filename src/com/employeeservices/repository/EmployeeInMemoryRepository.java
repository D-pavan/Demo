package com.employeeservices.repository;

import com.employeeservices.models.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class EmployeeInMemoryRepository {
    private static  EmployeeInMemoryRepository employeeInMemoryRepository;
    private final HashMap<Integer, Employee> employees;
    private final TreeMap<String, List<Integer>> employeeNames;
    private EmployeeInMemoryRepository(){
        employees=new HashMap<>();
        employeeNames=new TreeMap<>();
    }
    public static EmployeeInMemoryRepository getInstance(){
        if(employeeInMemoryRepository==null){
            employeeInMemoryRepository=new EmployeeInMemoryRepository();
        }
        return employeeInMemoryRepository;
    }
    public HashMap<Integer,Employee> getEmployees(){
        return  this.employees;
    }

    public TreeMap<String, List<Integer>> getEmployeeNames() {
        return employeeNames;
    }
}
