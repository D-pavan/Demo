package com.employeeservices.services;

import com.employeeservices.models.Employee;

import java.util.List;

public interface Services {
    public Employee getEmployeeById(int empId);
    public void addEmployee(Employee emp);
    public List<Employee> getAllEmployees();
    public void updateEmployee(int id,Employee emp);
    public Employee deleteEmployee(int empId);
    public List<Employee> getEmployeeByName(String name);
}
