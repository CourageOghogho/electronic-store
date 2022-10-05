package decagon.service;

import dev.decagon.entity.Customer;
import dev.decagon.entity.Product;
import dev.decagon.entity.Staff;
import dev.decagon.entity.Store;
import dev.decagon.enums.Role;
import dev.decagon.service.CashierServiceImpl;
import dev.decagon.service.CustomerServiceImpl;
import dev.decagon.service.StoreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Hashtable;

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
        cashierService=CashierServiceImpl.cashierService(cashier,store.getAvailableProducts());

        cup = new Product(10, "One-World glass cup", 2500.50f);

        storeService.createProductCategory("CUP");


        storeService.addNewProductToAvailability(cup);
        cup.setCategory("CUP");
        customer= new Customer();
        customer.setWalletBalance(5000000);
        customer.setName("Wale");
        customerService=CustomerServiceImpl.customerService(customer,store.getAvailableProducts());
    }

    @Test
    void customerService() {
        assertInstanceOf(CustomerServiceImpl.class,CustomerServiceImpl.customerService(new Customer(),new Hashtable()),
                "should return object of type CustomerServiceImpl");
    }

    @Test
    void buyProduct() {
        Product laptop =new Product();
        laptop.setName("laptop");
        laptop.setPrice(200);

        int beforeBuying= store.getAvailableProducts().get(cup);
        customerService.buyProduct(cup,cashierService,1);
        int afterBuying=store.getAvailableProducts().get(cup);

        assertNotEquals(afterBuying,beforeBuying);
    }

    @Test
    void addToCart() {
        Product laptop =new Product();
        laptop.setName("laptop");
        laptop.setPrice(200);
        Integer customerCartSizeBeforeAdding =customer.getCart().size();
        customerService.addToCart(laptop);
        Integer customerCartSizeAfterAdding =customer.getCart().size();
        assertNotEquals(customerCartSizeBeforeAdding,customerCartSizeAfterAdding);
    }

    @Test
    void removeFromCart() {
        customerService.addToCart(cup);
        Integer customerCartSizeBeforeAdding =customer.getCart().size();
        customerService.removeFromCart(cup);
        Integer customerCartSizeAfterAdding =customer.getCart().size();
        assertNotEquals(customerCartSizeBeforeAdding,customerCartSizeAfterAdding);
    }
}