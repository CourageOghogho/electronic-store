package dev.decagon.service;

import dev.decagon.entity.Customer;
import dev.decagon.entity.Product;
import dev.decagon.entity.Receipt;
import dev.decagon.exception.InsufficientFundException;

public interface CashierService {
    public Receipt disSpenceReceipt(Customer customer,Product product, Integer count);
    public Receipt sellProduct(Customer customer, Product product, Integer count)
            throws InsufficientFundException, NullPointerException;
}
