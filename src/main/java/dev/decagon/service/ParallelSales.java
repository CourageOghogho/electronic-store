package dev.decagon.service;

import dev.decagon.entity.Customer;

public class ParallelSales implements Runnable{
    private  final Customer customer;
    private final  CashierService cashierService;
    public ParallelSales(CashierService cashierService,Customer customer){
        this.cashierService=cashierService;
        this.customer=customer;
    }
    @Override
    public void run() {
        cashierService.sellParallel(customer);
    }
}
