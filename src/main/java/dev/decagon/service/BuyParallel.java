package dev.decagon.service;

import dev.decagon.entity.Customer;
import dev.decagon.entity.Product;

public class BuyParallel extends Thread{

    private  final CustomerService service;
    private  final Customer customer;
    private  final Product product;
    private  final Integer count;
    public  BuyParallel(CustomerService service, Customer customer, Product product, Integer count){
        this.customer=customer;
        this.service =service;
        this.product=product;
        this.count=count;

    }

    @Override
    public void run() {
        service.buyProduct(customer,product,count);
    }

    public Customer getCustomer() {
        return customer;
    }
}
