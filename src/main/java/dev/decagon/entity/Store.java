package dev.decagon.entity;

import java.util.*;

public class Store {
    private String name;
    private Address address;

    private final Set<String> productCategory=new HashSet();
    private final Hashtable<Product,Integer> availableProducts =new Hashtable<>();
    private final List<Staff> employees=new ArrayList<>();
    private final List<Applicant> applicantsPool=new ArrayList<>();
    public Store() {
    }

    public Store(String name, Address address) {
        this.name = name;
        this.address = address;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public List<Staff> getEmployees() {
        return employees;
    }
    public List<Applicant> getApplicantsPool() {
        return applicantsPool;
    }

    public Set<String> getProductCategory() {
        return productCategory;
    }

    public Hashtable<Product, Integer> getAvailableProducts() {
        return availableProducts;
    }
}
