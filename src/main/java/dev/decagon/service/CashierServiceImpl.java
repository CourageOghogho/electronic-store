package dev.decagon.service;

import dev.decagon.entity.Customer;
import dev.decagon.entity.Product;
import dev.decagon.entity.Receipt;
import dev.decagon.entity.Staff;
import dev.decagon.exception.InsufficientFundException;
import dev.decagon.exception.InvalidNumberOfItemsException;
import dev.decagon.exception.NoSuchProductCategoryExistsException;
import dev.decagon.exception.ProductOutOfStockException;

import java.util.Hashtable;
import java.util.Objects;

public class CashierServiceImpl implements CashierService {
    private Staff cashier;
    private  Hashtable<Product,Integer> availableProduct;

    private CashierServiceImpl(Staff cashier, Hashtable<Product,Integer> availableProduct){
        this.cashier=cashier;
        this.availableProduct=availableProduct;

    }

    public static CashierServiceImpl cashierService(Staff cashier,Hashtable<Product,Integer> availableProduct){
        return new CashierServiceImpl(cashier,availableProduct);
    }
    public Receipt disSpenceReceipt(Customer customer, Product product, Integer count) {

        Receipt receipt= new Receipt();
        receipt.setCustomerName(customer.getName());
        receipt.setItemName(product.getName());
        receipt.setNumberOfItem(count);
        receipt.setUnitCost(product.getPrice());
        receipt.setMessage("Thanks for patronizing!");
        receipt.setTotalCost(product.getPrice()*count);

        return receipt;
    }


    public Receipt sellProduct(Customer customer,Product product,Integer count){
        if(customer==null || product== null)
            throw new NullPointerException("Customer or Product is not referenced");
        if(customer.getWalletBalance()< product.getPrice())
            throw new InsufficientFundException("Low balance");
        if(count<1) throw new InvalidNumberOfItemsException("The items cannot be less than one");
        for (Product item:availableProduct.keySet()
             ) {
            if(Objects.equals(item.getName(), product.getName())){
                if(availableProduct.get(item)>=count){
                    availableProduct.replace(item,availableProduct.get(item),
                            availableProduct.get(item)-count);
                    customer.setWalletBalance(customer.getWalletBalance()-(item.getPrice()*count));
                    return disSpenceReceipt(customer,product,count);
                }else {
                    throw new ProductOutOfStockException("Products available is not sufficient for your order");
                }
            }
        }throw new NoSuchProductCategoryExistsException("Product category not recognised");

    }


}
