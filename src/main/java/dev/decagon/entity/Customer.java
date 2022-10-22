package dev.decagon.entity;

import dev.decagon.enums.Gender;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Customer extends User {

    private double walletBalance=00.00;
    private Address address;
    private List<Receipt> receipts=new ArrayList<>();

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

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public Hashtable<Product,Integer> getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "walletBalance=" + walletBalance +
                ", address=" + address +
                ", receipts=" + receipts +
                ", cart=" + cart +
                '}';
    }
}
