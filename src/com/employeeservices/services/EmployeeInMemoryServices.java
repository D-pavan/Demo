package com.employeeservices.services;

import com.employeeservices.exceptions.DuplicateValueException;
import com.employeeservices.exceptions.EmployeeNotFoundException;
import com.employeeservices.models.Address;
import com.employeeservices.models.Employee;
import com.employeeservices.repository.AddressInMemoryRepository;
import com.employeeservices.repository.EmployeeInMemoryRepository;

import java.util.*;

public class EmployeeInMemoryServices implements  Services{
    private static EmployeeInMemoryServices employeeInMemoryServices;
    private final HashMap<Integer,Employee> employees;
    private final HashMap<Integer,Address> addresses;
    private final TreeMap<String,List<Integer>> employeeNames;
    private  EmployeeInMemoryServices(){
        employees=EmployeeInMemoryRepository.getInstance().getEmployees();
        addresses= AddressInMemoryRepository.getInstance().getAddress();
        employeeNames=EmployeeInMemoryRepository.getInstance().getEmployeeNames();
    }
    public  static EmployeeInMemoryServices getInstance(){
        if(employeeInMemoryServices==null){
            employeeInMemoryServices=new EmployeeInMemoryServices();
        }
        return employeeInMemoryServices;
    }
    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> emps=new ArrayList<>();
        for(Map.Entry<Integer,Employee> emp:employees.entrySet()){
            emp.getValue().setAddress(addresses.get(emp.getKey()));
            emps.add(emp.getValue());
        }
        return emps;
    }
    @Override
    public Employee deleteEmployee(int empId) {
        Employee deletedEmployee=null;
        try{
            if(employees.containsKey(empId)){
                deletedEmployee=employees.remove(empId);
                if(addresses.containsKey(empId)) deletedEmployee.setAddress(addresses.remove(empId));
                if(employeeNames.containsKey(deletedEmployee.getEmpName())) {
                        List<Integer> ids=employeeNames.get(deletedEmployee.getEmpName());
                        for(int i=0;i<ids.size();i++){
                            if(ids.get(i)==empId){
                                employeeNames.get(deletedEmployee.getEmpName()).remove(i);
                                break;
                            }
                        }
                        if(employeeNames.get(deletedEmployee.getEmpName()).isEmpty()){
                            employeeNames.remove(deletedEmployee.getEmpName());
                        }
                }
            }
            if (deletedEmployee == null) throw new EmployeeNotFoundException("No Employee Found with given Id!!!");

        }
        catch (EmployeeNotFoundException e){
            System.out.println(e);
        }
        return deletedEmployee;
    }
    @Override
    public Employee getEmployeeById(int empId) {
        Employee emp=null;
        try {
            if (employees.containsKey(empId)) {
                emp = employees.get(empId);
                if (addresses.containsKey(empId)) emp.setAddress(addresses.get(empId));
            }
            if (emp == null) {
                throw new EmployeeNotFoundException("No employee found with given ID!!!!");
            }
        }
        catch (EmployeeNotFoundException e){
            System.out.println(e);
        }
        return emp;
    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        List<Employee> emps=new ArrayList<>();
        try{
            if(!employeeNames.containsKey(name)) throw  new EmployeeNotFoundException("No employee found with given name..");
            else{
                List<Integer> ids=employeeNames.get(name);
                for(Integer id:ids){
                     emps.add(employees.get(id));
                     emps.getLast().setAddress(addresses.get(id));
                }
            }
        }
        catch (EmployeeNotFoundException  e){
            System.out.println(e);
        }
        return emps;
    }

    @Override
    public void addEmployee(Employee emp) {
         try {
             if(employees.containsKey(emp.getEmpId())) throw new DuplicateValueException("Duplicate ID..");
             else {
                 addresses.put(emp.getEmpId(), emp.getAddress());
                 emp.setAddress(null);
                 employees.put(emp.getEmpId(), emp);
                 if (!employeeNames.containsKey(emp.getEmpName())) {
                     employeeNames.put(emp.getEmpName(), new ArrayList<>());
                 }
                 employeeNames.get(emp.getEmpName()).add(emp.getEmpId());
             }
         }
         catch(DuplicateValueException | NullPointerException e){
             System.out.println(e);
         }

    }
    @Override
    public void updateEmployee(int empId, Employee emp) {
           try{
               if(!employees.containsKey(empId)) throw new EmployeeNotFoundException("Employee not found with given ID"+empId);
               else {
                   String name=employees.get(empId).getEmpName();
                   List<Integer> ids=employeeNames.getOrDefault(name,new ArrayList<>());
                   employeeNames.remove(name);
                   employeeNames.put(emp.getEmpName(),ids);
                   if(addresses.containsKey(empId)){
                       addresses.put(empId,emp.getAddress());
                   }
                   emp.setAddress(null);
                   employees.put(empId,emp);
               }
           }
           catch (EmployeeNotFoundException e){
               System.out.println(e);
           }
    }

}
