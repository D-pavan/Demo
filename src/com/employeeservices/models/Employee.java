package com.employeeservices.models;

import java.util.Objects;

public class Employee {
    private  int empId;
    private String empName;
    private Address address;
    public Employee(){
        this(1,"Eshwar",new Address("hyderabad",2030));
    }
    public Employee(int empId, String empName, Address address) {
        this.empId = empId;
        this.empName = empName;
        this.address = address;

    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return getEmpId() == employee.getEmpId() && Objects.equals(getEmpName(), employee.getEmpName()) && Objects.equals(getAddress(), employee.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmpId(), getEmpName(), getAddress());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", address=" + address +
                '}';
    }
}
