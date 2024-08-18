package com.employeeservices.controllers;

import com.employeeservices.models.Department;
import com.employeeservices.models.Employee;
import com.employeeservices.services.EmployeeInMemoryServices;
import com.employeeservices.utils.InputUtil;
import com.employeeservices.utils.OutputUtil;

import java.util.ArrayList;
import java.util.List;

public class EmployeeInMemoryController implements  Controllers{
    private  static EmployeeInMemoryController employeeInMemoryController;
    private final EmployeeInMemoryServices employeeInMemoryServices;
    private  final OutputUtil outputUtil;
    private final InputUtil inputUtil;
    private EmployeeInMemoryController(){
        employeeInMemoryServices=EmployeeInMemoryServices.getInstance();
        inputUtil=InputUtil.getInstance();
        outputUtil=OutputUtil.getInstance();
    }
    public  static  EmployeeInMemoryController getInstance(){
        if(employeeInMemoryController==null) {
            employeeInMemoryController=new EmployeeInMemoryController();
        }
        return employeeInMemoryController;
    }
    @Override
    public  void startOperations(){
        while(true) {
            try {
                System.out.println("Enter a number from menu : \n 1.Add Employee \n 2.Update Employee\n 3.Delete Employee\n 4.Display Employees \n 5.Get Employee By Id\n 6.Get Employee by name\n 7.Exit");
                int menuNumber=Integer.parseInt(inputUtil.readString());
                if(menuNumber==7) break;
                switch (menuNumber) {
                    case 1: {
                        System.out.println("Enter the required Details : \n");
                        Employee emp = null;
                        Department dept = null;
                        List<Object> objs=inputUtil.readInput(emp, dept);
                        emp=(Employee) objs.get(0);
                        dept=(Department) objs.get(1);
                        employeeInMemoryServices.addEmployee(emp);
                        break;
                    }
                    case 2: {
                        System.out.println("Enter the required Details : \n");
                        System.out.println("Enter the Employee Id to be updated : ");
                        int empId = Integer.parseInt(inputUtil.readString());
                        Employee emp = null;
                        Department dept = null;
                        List<Object> objs=inputUtil.readInput(emp, dept);
                        emp=(Employee) objs.get(0);
                        dept=(Department) objs.get(1);
                        employeeInMemoryServices.updateEmployee(empId, emp);
                        //System.out.println("Employee updated Successfully...\n");
                        break;
                    }
                    case 3: {
                        System.out.println("Enter the employee Id to be deleted : ");
                        int empId = Integer.parseInt(inputUtil.readString());
                        Employee emp = employeeInMemoryServices.deleteEmployee(empId);
                        List<Employee> emps=new ArrayList<>();
                        emps.add(emp);
                        System.out.print("Details of deleted Employee : \n");
                        outputUtil.printEmployees(emps);
                        break;
                    }
                    case 4: {
                        System.out.println("Employees Details: \n");
                        List<Employee> employees = employeeInMemoryServices.getAllEmployees();
                        if(employees.isEmpty()){
                            System.out.println("Empty no employees!!");
                            break;
                        }
                        outputUtil.printEmployees(employees);
                        break;
                    }
                    case 5: {
                        System.out.println("Enter the Employee Id :");
                        int empId = Integer.parseInt(inputUtil.readString());
                        System.out.println("Employee Details : \n");
                        List<Employee> emp=new ArrayList<>();
                        emp.add(employeeInMemoryServices.getEmployeeById(empId));
                        outputUtil.printEmployees(emp);
                        break;
                    }
                    case 6:{
                        System.out.println("Enter the employee name :");
                        String name= inputUtil.readString();
                        List<Employee> emps=employeeInMemoryServices.getEmployeeByName(name);
                        outputUtil.printEmployees(emps);
                        break;
                    }
                    default:
                        System.out.println("Invalid menu option!!!");
                }
            }
            catch (NumberFormatException e){
                System.out.println("Number format invalid enter numerical value: "+e);
            }
            catch (NullPointerException e){
                System.out.println(e);
            }
        }

    }
}
