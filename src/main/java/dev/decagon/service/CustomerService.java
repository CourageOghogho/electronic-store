package dev.decagon.service;

import dev.decagon.entity.Product;
import dev.decagon.entity.Receipt;

public interface CustomerService {
   public Receipt buyProduct(Product product, CashierServiceImpl cashierService,Integer count);
    public Product removeFromCart(Product product);
    public void addToCart(Product product);
}
