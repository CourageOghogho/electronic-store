package dev.decagon.entity;

import dev.decagon.enums.OrderStatus;

import java.util.Hashtable;
import java.util.UUID;

public class Order  {
    UUID orderId;
    private Customer customer;
    private final Hashtable<Product,Integer> orderedProductList;
    private OrderStatus orderStatus;

    private  Receipt receipt;

    public Order() {
        orderedProductList=new Hashtable<>();
    }


    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Hashtable<Product, Integer> getProductList() {
        return orderedProductList;
    }

}
