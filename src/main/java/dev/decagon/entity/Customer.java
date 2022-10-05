package dev.decagon.entity;

import dev.decagon.enums.Gender;

import java.util.Hashtable;
import java.util.List;

public class Customer extends User {

    private double walletBalance=00.00;
    private Address address;
    private List<Product> order;

    private Hashtable<Product,Integer> cart=new Hashtable<>();

    public Customer() {
    }

    public Customer(String name, String email, Gender gender, Address address) {
        super(name, email, gender);
        this.walletBalance = walletBalance;
        this.address = address;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;

    }

    public Hashtable<Product,Integer> getCart() {
        return cart;
    }

   }
