package com.employeeservices.repository;

import java.io.*;

public class AddressFileSystemRepository {
    private static  AddressFileSystemRepository addressFileSystemRepository;
    private final BufferedReader bufferedReader;
    private  final BufferedWriter bufferedWriter;
    private  AddressFileSystemRepository() throws IOException {
        bufferedReader=new BufferedReader(new FileReader("D://pavan_Internship/address.txt"));
        bufferedWriter=new BufferedWriter(new FileWriter("D://pavan_Internship/address.txt",true));
    }
    public static AddressFileSystemRepository getInstance(){
        try{
            if(addressFileSystemRepository==null){
                addressFileSystemRepository=new AddressFileSystemRepository();
            }
        }
        catch (IOException e){
            System.out.println(e);
        }
        return addressFileSystemRepository;
    }
    public BufferedWriter getBufferedWriter(){
        return  this.bufferedWriter;
    }
    public  BufferedReader getBufferedReader(){
        return this.bufferedReader;
    }
    public  BufferedReader resetReader() throws  IOException{
        return new BufferedReader(new FileReader("D://pavan_Internship/address.txt"));
    }
    public BufferedWriter resetWriter() throws  IOException{
        return new BufferedWriter(new FileWriter("D://pavan_Internship/address.txt",true));
    }
}
