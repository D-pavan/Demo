package com.employeeservices.services;

import com.employeeservices.controllers.EmployeeFileSystemController;
import com.employeeservices.models.Address;
import com.employeeservices.models.Department;
import com.employeeservices.models.Employee;
import com.employeeservices.repository.AddressFileSystemRepository;
import com.employeeservices.repository.EmployeeFileSystemRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFileSystemServices implements  Services {
    private  static EmployeeFileSystemServices employeeFileSystemServices;
    private  BufferedReader empReader;
    private  BufferedWriter empWriter;
    private  BufferedReader addressReader;
    private  BufferedWriter addressWriter;
    private final EmployeeFileSystemRepository employeeFileSystemRepository;
    private final AddressFileSystemRepository addressFileSystemRepository;
    private  EmployeeFileSystemServices() throws  IOException{
        employeeFileSystemRepository=EmployeeFileSystemRepository.getInstance();
        addressFileSystemRepository= AddressFileSystemRepository.getInstance();
        empReader=employeeFileSystemRepository.getBufferedReader();
        empWriter=employeeFileSystemRepository.getBufferedWriter();
        addressReader=addressFileSystemRepository.getBufferedReader();
        addressWriter=addressFileSystemRepository.getBufferedWriter();
    }
    public static  EmployeeFileSystemServices getInstance(){
        try {
            if (employeeFileSystemServices == null) {
                employeeFileSystemServices = new EmployeeFileSystemServices();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return employeeFileSystemServices;
    }
    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            empReader=employeeFileSystemRepository.resetReader();
            addressReader=addressFileSystemRepository.resetReader();
            String line=null;
            while ((line=empReader.readLine())!= null) {
                String empData[]=line.split(",");
                employees.add(new Employee(Integer.parseInt(empData[0]),empData[1],  getAddress(Integer.parseInt(empData[0]))));
            }
            //dispaly();
        }
        catch (Exception e){
            System.out.println("Error in reading.. "+e);
        }
        return  employees;
    }

    @Override
    public void addEmployee(Employee emp) {
        try{
            String empData=emp.getEmpId()+","+emp.getEmpName()+"\n";
            String addressData=emp.getEmpId()+","+emp.getAddress().getLocation()+","+emp.getAddress().getPincode()+"\n";
            addressWriter.write(addressData);
            empWriter.write(empData);
            addressWriter.flush();
            empWriter.flush();
        }
        catch (Exception e){
            System.out.println("Error in writing.. "+e);
        }
        System.out.println("employee added..");
    }

    @Override
    public void updateEmployee(int id, Employee emp) {

    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        return List.of();
    }

    @Override
    public Employee getEmployeeById(int empId) {
        Employee emp=null;
        try {

            String empData[]=getLine(empId,0).split(",");
            String addressData[]=getLine(empId,1).split(",");
            emp=new Employee(Integer.parseInt(empData[0]),empData[1],new Address(addressData[1],Long.parseLong(addressData[2])));

        } catch (Exception e) {
            System.out.println(e);
        }
        return emp;
    }

    @Override
    public Employee deleteEmployee(int empId) {
        return null;
    }
    public Address getAddress(int empId){
        Address address=null;

        try {
            addressReader=addressFileSystemRepository.resetReader();
            String line=null;
            while ((line = addressReader.readLine()) != null) {
                int i, id;
                String number = "";
                for (i = 0; i < line.length() && line.charAt(i) != ','; i++) {
                    number += line.charAt(i);
                }
                id = Integer.parseInt(number);
                if (id == empId) {
                    String data[]=line.split(",");
                    address=new Address(data[1],Long.parseLong(data[2]));
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("getting address "+e);
        }
        if(address==null){
            System.out.println("no address found");
        }
        return address;

    }
    public String getLine(int empId,int arg){
        String line=null;
        try {
            empReader=employeeFileSystemRepository.resetReader();
            addressReader=addressFileSystemRepository.resetReader();
            if (arg == 0) {
                while ((line = empReader.readLine()) != null) {
                    int i, id;
                    String number = "";
                    for (i = 0; i < line.length() && line.charAt(i) != ','; i++) {
                        number += line.charAt(i);
                    }
                    id = Integer.parseInt(number);
                    if (id == empId) {
                        break;
                    }
                }

            } else {
                while ((line = addressReader.readLine()) != null) {
                    int i, id;
                    String number = "";
                    for (i = 0; i < line.length() && line.charAt(i) != ','; i++) {
                        number += line.charAt(i);
                    }
                    id = Integer.parseInt(number);
                    if (id == empId) {
                        break;
                    }
                }
            }

        }
        catch (IOException e){
            System.out.println(e);
        }
        return line;
    }
    public void dispaly(){
        try {
            empReader=employeeFileSystemRepository.resetReader();
            String line=null;
            while ((line=empReader.readLine())!=null){
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
