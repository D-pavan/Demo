package com.employeeservices.models;

import java.util.Objects;

public class Address {
    private String location;
    private long pincode;

    public Address(){
        this("Hyderabad",1020);
    }
    public Address(String location, long pincode) {
        this.location = location;
        this.pincode = pincode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getPincode() {
        return pincode;
    }

    public void setPincode(long pincode) {
        this.pincode = pincode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return getPincode() == address.getPincode() && Objects.equals(getLocation(), address.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocation(), getPincode());
    }

    @Override
    public String toString() {
        return "Address{" +
                "location='" + location + '\'' +
                ", pincode=" + pincode +
                '}';
    }
}
