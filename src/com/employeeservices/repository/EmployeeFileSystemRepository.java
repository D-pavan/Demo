package com.employeeservices.repository;

import com.employeeservices.models.Employee;

import java.io.*;


public class EmployeeFileSystemRepository {
     private  static EmployeeFileSystemRepository employeeFileSystemRepository;
     private final BufferedReader bufferedReader;
     private final BufferedWriter bufferedWriter;
     private EmployeeFileSystemRepository() throws IOException {
         bufferedReader=new BufferedReader(new FileReader("D://pavan_Internship/employee.txt"));
         bufferedWriter=new BufferedWriter(new FileWriter("D://pavan_Internship/employee.txt",true));
     }
     public static EmployeeFileSystemRepository getInstance(){
         if(employeeFileSystemRepository==null){
             try {
                 employeeFileSystemRepository = new EmployeeFileSystemRepository();
             }
             catch (IOException e){
                 System.out.println(e);
             }
         }
         return employeeFileSystemRepository;
     }
     public BufferedWriter getBufferedWriter(){
         return  this.bufferedWriter;
     }
     public  BufferedReader getBufferedReader(){
         return this.bufferedReader;
     }
     public  BufferedReader resetReader() throws  IOException{
         return new BufferedReader(new FileReader("D://pavan_Internship/employee.txt"));
     }
     public BufferedWriter resetWriter() throws  IOException{
         return new BufferedWriter(new FileWriter("D://pavan_Internship/employee.txt",true));
     }
}
