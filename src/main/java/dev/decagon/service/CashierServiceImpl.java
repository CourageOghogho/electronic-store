package dev.decagon.service;

import dev.decagon.entity.*;
import dev.decagon.exception.InsufficientFundException;
import dev.decagon.exception.ProductOutOfStockException;

import java.util.Hashtable;
import java.util.Map;
import java.util.Queue;

public class CashierServiceImpl implements CashierService {

    final private  Hashtable<Product,Integer> availableProduct;
    final private  Store store;

    public CashierServiceImpl(Store store){
        this.availableProduct=store.getAvailableProducts();
        this.store=store;

    }


    public Receipt disSpenceReceipt(Customer customer,double totalCost){
        Receipt receipt=  new Receipt();
        receipt.setCustomerName(customer.getName());
        receipt.setItemName(itemNameFormatter(customer.getCart()));
        receipt.setMessage("Thanks for coming!");
        receipt.setTotalCost(totalCost);
        return receipt;
    }

    private String itemNameFormatter(Map<Product,Integer> map){
        StringBuilder itemName= new StringBuilder();
        for (Product product: map.keySet()){
            itemName.append(" ").append(product.getName()).append(" \n");
        }
        return itemName.toString();
    }
    public void sellParallel(Customer customer){
        processSales(customer);
    }
    public  void sell(Queue<Customer> queue){
        while (!queue.isEmpty()){
            Customer customer=queue.poll();
            processSales(customer);
        }

    }

    private void  processSales(Customer customerServing){
        int itemCount=0;
        double totalAmount=0.0;
        for (Product product: customerServing.getCart().keySet()
        ) {
            totalAmount+=product.getPrice()*customerServing.getCart().get(product);
            itemCount+=customerServing.getCart().get(product);
            if (customerServing.getWalletBalance()<totalAmount)
                throw new InsufficientFundException("Your current balance if too low for this purchase");
            if (customerServing.getCart().get(product)>availableProduct.get(product))
                throw new ProductOutOfStockException("Products no longer available");
            store.getAvailableProducts().replace(product,
                    store.getAvailableProducts().get(product),
                    store.getAvailableProducts().get(product)-customerServing.getCart().get(product)
                    );

        }
        customerServing.setWalletBalance(
                customerServing.getWalletBalance()-totalAmount);
        customerServing.getCart().clear();
        Receipt receipt=disSpenceReceipt(customerServing,totalAmount);
        receipt.setNumberOfItem(itemCount);
        customerServing.getReceipts().add(receipt);
    }


}
