package com.employeeservices.utils;

import com.employeeservices.models.Employee;

import java.util.List;

public class OutputUtil {
    private static OutputUtil outputUtil;
    private  OutputUtil(){}
    public  static  OutputUtil getInstance(){
        if(outputUtil==null){
            outputUtil=new OutputUtil();
        }
        return outputUtil;
    }
    public void printEmployees(List<Employee> employees){
        System.out.printf("%-15s %-15s %-15s %-15s\n","empID","empName","Location","Pin code");
        String line="";
        for(int i=0;i<20;i++) line+="----";
        System.out.println(line);
        for(Employee e:employees){
            System.out.printf("%-15s %-15s %-15s %-15s\n",e.getEmpId(),e.getEmpName(),e.getAddress().getLocation(),e.getAddress().getPincode());
            System.out.println(line);
        }
    }
}
