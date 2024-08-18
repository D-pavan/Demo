package com.employeeservices.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department {
    private int deptId;
    private  String deptName;
    private List<Employee> employee;
    public Department(){
        this(101,"Developer",new ArrayList<>());
    }
    public Department(int deptId, String deptName,List<Employee> employee) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.employee=employee;
    }

    public int getDeptId() {
        return deptId;
    }
    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department that)) return false;
        return getDeptId() == that.getDeptId() && Objects.equals(getDeptName(), that.getDeptName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDeptId(), getDeptName());
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
