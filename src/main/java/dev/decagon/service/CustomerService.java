package dev.decagon.service;

import dev.decagon.entity.Customer;
import dev.decagon.entity.Product;

public interface CustomerService {
    void buyProduct(Customer customer, Product product, Integer count);
    Product removeFromCart(Customer customer,Product product);
    void addToCart(Customer customer,Product product,Integer count);

    Product searchForProductByName(String s);
}
