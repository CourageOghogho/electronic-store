package dev.decagon.service;

import dev.decagon.entity.Customer;
import dev.decagon.entity.Receipt;

import java.util.Queue;

public interface CashierService {

   // public Receipt disSpenceReceipt(Order order, Double totalCost);

   // public  Receipt sellProducts(Order order);
    void sell(Queue<Customer> queue);
    void sellParallel(Customer customer);
    Receipt disSpenceReceipt(Customer customer,double totalCost);


}
