package dev.decagon.service;

import dev.decagon.entity.Customer;
import dev.decagon.entity.Product;
import dev.decagon.entity.Receipt;
import dev.decagon.exception.InsufficientFundException;
import dev.decagon.exception.ProductNotInCart;

import java.util.Hashtable;
import java.util.Objects;

public class CustomerServiceImpl implements CustomerService {

    private final Customer customer;
    Hashtable<Product,Integer> availableProductsInStore;

    private CustomerServiceImpl(Customer customer, Hashtable<Product,Integer> availableProductsInStore){
        this.customer=customer;
        this.availableProductsInStore=availableProductsInStore;
    }

    public static CustomerServiceImpl customerService(Customer customer,Hashtable<Product,Integer> availableProductsInStore){
        return new CustomerServiceImpl(customer,availableProductsInStore);
    }


    public Receipt buyProduct(Product product, CashierServiceImpl cashierService, Integer count)
            throws InsufficientFundException{
            return cashierService.sellProduct(customer, product,count);
    }

    public void addToCart(Product product){

        for (Product item:this.customer.getCart().keySet()
        ) {
            if(item.getName()== product.getName()){

                customer.getCart().replace(item,customer.getCart().get(item),
                        customer.getCart().get(item)+1);
                return;
            }
        }

        customer.getCart().put(product,1);
    }



    public Product removeFromCart(Product product)  {
        for (Product item:customer.getCart().keySet()
        ) {
            if(Objects.equals(item.getName(), product.getName())){
                customer.getCart().remove(item);
                return item;
            }
        }
        throw new ProductNotInCart(product.getName()+" is not in this cart");
    }

}
