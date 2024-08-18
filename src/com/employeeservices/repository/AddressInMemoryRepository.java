package com.employeeservices.repository;

import com.employeeservices.models.Address;

import java.util.HashMap;

public class AddressInMemoryRepository {
    private static  AddressInMemoryRepository addressInMemoryRepository;
    private final HashMap<Integer, Address> address;
    private AddressInMemoryRepository(){
        this.address=new HashMap<>();
    }
    public static AddressInMemoryRepository getInstance(){
        if(addressInMemoryRepository==null){
            addressInMemoryRepository=new AddressInMemoryRepository();
        }
        return addressInMemoryRepository;
    }
    public HashMap<Integer,Address> getAddress(){
        return this.address;
    }
}
