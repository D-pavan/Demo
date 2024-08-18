
package com.employeeservices.utils;

import com.employeeservices.models.Address;
import com.employeeservices.models.Department;
import com.employeeservices.models.Employee;

import java.util.*;

public class InputUtil {
    private  static InputUtil inputUtil;
    private final Scanner scan;
    private InputUtil(){
        scan=new Scanner(System.in);
    }
    public static InputUtil getInstance() {
         if(inputUtil==null){
             inputUtil=new InputUtil();
         }
         return inputUtil;
    }
    public String readString(){
        return scan.next();
    }
    public List<Object> readInput(Employee emp, Department dept){
        List<Object> obj = new ArrayList<>();
        try {
            System.out.println("Enter employee Id: ");
            int empId = scan.nextInt();
            System.out.println("Enter employee Name :");
            String empName = scan.next();
            System.out.println("Enter the location : ");
            String location = scan.next();
            System.out.println("Enter the pin code : ");
            long pincode = scan.nextLong();
            System.out.println("Enter the DepartmentId : ");
            int deptId = scan.nextInt();
            System.out.println("Enter the Department Name : ");
            String deptName = scan.next();
            obj.add(new Employee(empId, empName, new Address(location, pincode)));
            obj.add(new Department(deptId, deptName, new ArrayList<>()));
            return obj;
        } catch (InputMismatchException e) {
            obj=null;
            System.out.println("enter id in numeric format"+e);
        }
        return obj;
    }
}
