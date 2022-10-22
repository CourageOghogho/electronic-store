package dev.decagon.service;

import dev.decagon.entity.Customer;
import dev.decagon.entity.Product;
import dev.decagon.entity.Store;
import dev.decagon.exception.InsufficientFundException;
import dev.decagon.exception.NoSuchProductInAvailabilityException;
import dev.decagon.exception.ProductNotInCart;
import dev.decagon.exception.ProductOutOfStockException;

import java.util.Hashtable;
import java.util.Objects;

public class CustomerServiceImpl implements CustomerService {
    private final Store store;
    private final Hashtable<Product,Integer> availableProductsInStore;

    public CustomerServiceImpl(Store store) {
        availableProductsInStore=store.getAvailableProducts();
        this.store=store;

    }

    public void buyProduct(Customer customer,Product product, Integer count) {
        if (customer == null || product == null || count < 1)
            throw new IllegalArgumentException("Customer, or product cannot be null, and count cannot be less than 1");

        if (product.getPrice() * count <= customer.getWalletBalance()) {
            Integer currentProductCount= availableProductsInStore.get(product);
            if (currentProductCount!=null){
                if (currentProductCount >= count) {
                    addToCart(customer, product, count);
                    store.getSalesQueue().offer(customer);
                    store.getSalesPriorityQueue().offer(customer);
                    return;
                } else {
                    if (currentProductCount > 0) {
                        addToCart(customer, product, currentProductCount);
                        store.getSalesQueue().offer(customer);
                        store.getSalesPriorityQueue().offer(customer);
                        throw new ProductOutOfStockException(currentProductCount + " added to cart, " +
                                "Products in the store not enough");
                    } else {
                        throw new ProductOutOfStockException("Products not available in store");
                    }

                }

            }

        }throw new InsufficientFundException("You don't have enough balance");
    }


    public void addToCart(Customer customer,Product product,Integer count){
        if(customer==null||product==null)throw new NullPointerException("Customer or Product cannot be none");

        for (Product item:customer.getCart().keySet()
        ) {
            if(Objects.equals(item.getName(), product.getName())){

                customer.getCart().replace(item,customer.getCart().get(item),
                        customer.getCart().get(item)+count);
                return;
            }
        }

        customer.getCart().put(product,count);
    }

    @Override
    public Product searchForProductByName(String productName) {
        for (Product product:store.getAvailableProducts().keySet()
             ) {
            if(product.getName().equals(productName))
                return product;
        }
        throw new NoSuchProductInAvailabilityException("No product with "+productName+" exists");
    }


    public Product removeFromCart(Customer customer,Product product)  {
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

