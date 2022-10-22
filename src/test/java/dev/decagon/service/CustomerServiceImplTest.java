package dev.decagon.service;

import dev.decagon.entity.Customer;
import dev.decagon.entity.Product;
import dev.decagon.entity.Staff;
import dev.decagon.entity.Store;
import dev.decagon.enums.Role;
import dev.decagon.exception.InsufficientFundException;
import dev.decagon.exception.ProductOutOfStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {

    Store store;
    StoreServiceImpl storeService;
    CustomerServiceImpl customerService;
    Customer customer;
    CashierServiceImpl cashierService;
    Product cup;


    @BeforeEach
    void setUp() {
        store=new Store();
        store.setName("Courage Store");
        storeService=new StoreServiceImpl(store);
        Staff cashier=new Staff();
        cashier.setRole(Role.CASHIER);

        cashierService=new CashierServiceImpl(store);

        cup = new Product(10, "One-World glass cup", 2500.50f);

        storeService.createProductCategory("CUP");


        storeService.addNewProductToAvailability(cup);
        cup.setCategory("CUP");
        customer= new Customer();
        customer.setWalletBalance(5000000);
        customer.setName("Wale");
        customerService= new CustomerServiceImpl(store);
    }

    @Test
    void customerService() {
        assertInstanceOf(CustomerServiceImpl.class,new CustomerServiceImpl(store),
                "should return object of type CustomerServiceImpl");
    }

    @Test
    void buyProduct() {

        int beforeBuying= store.getAvailableProducts().get(cup);
        try {
            customerService.buyProduct(customer,cup,1);
            Customer customer1=store.getSalesPriorityQueue().peek();
            cashierService.sell(store.getSalesQueue());
        }catch (ProductOutOfStockException | InsufficientFundException e){
            e.printStackTrace();
        }


        int afterBuying=store.getAvailableProducts().get(cup);

        assertNotEquals(afterBuying,beforeBuying);
    }

    @Test
    void addToCart() {
        Product laptop =new Product();
        laptop.setName("laptop");
        laptop.setPrice(200);
        Integer customerCartSizeBeforeAdding =customer.getCart().size();
        customerService.addToCart(customer,laptop,1);
        Integer customerCartSizeAfterAdding =customer.getCart().size();
        assertNotEquals(customerCartSizeBeforeAdding,customerCartSizeAfterAdding);
    }

    @Test
    void removeFromCart() {
        customerService.addToCart(customer,cup,1);
        Integer customerCartSizeBeforeAdding =customer.getCart().size();
        customerService.removeFromCart(customer,cup);
        Integer customerCartSizeAfterAdding =customer.getCart().size();
        assertNotEquals(customerCartSizeBeforeAdding,customerCartSizeAfterAdding);
    }

}