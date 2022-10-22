package dev.decagon.service;

import dev.decagon.dao.ProductCategoryReaderService;
import dev.decagon.dao.ProductCategoryReaderServiceImpl;
import dev.decagon.dao.ProductReaderService;
import dev.decagon.dao.ProductReaderServiceImpl;
import dev.decagon.entity.*;
import dev.decagon.enums.Role;
import dev.decagon.exception.InsufficientFundException;
import dev.decagon.exception.ProductOutOfStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashierServiceImplTest {

    Store store;
    StoreServiceImpl storeService;

    Product cup;
    CashierServiceImpl cashierService;

    Customer customer;

    CustomerService customerService;




    @BeforeEach
    void setUp() {
        store = new Store();
        store.setName("Courage Store");

        cup = new Product(10, "One-World glass cup", 2500.50f);

        storeService = new StoreServiceImpl(store);
        Staff cashier = new Staff();
        cashier.setRole(Role.CASHIER);
        cashierService = new CashierServiceImpl( store);

        customerService = new CustomerServiceImpl(store);

        customer = new Customer();
        customer.setName("Okorie");

        String categoryPath = "/Users/mac/Documents/decagon-assignments/week-three-sq012-CourageOghogho/e-s" +
                "tore-app/src/main/resources/static/productCategory.csv";

        String productsListPath = "/Users/mac/Documents/decagon-assignments/week-three-sq012-CourageOgho" +
                "gho/e-store-app/src/main/resources/static/productsAvailable.csv";

        ProductCategoryReaderService productCategoryReaderService =
                new ProductCategoryReaderServiceImpl(store.getProductCategory());

        ProductReaderService productReaderService = new
                ProductReaderServiceImpl(store.getAvailableProducts());


        productCategoryReaderService.productCategoryReader(categoryPath);
        productReaderService.productReader(productsListPath);



    }

    @Nested
    class ReceiptTest {


        @Test
        @DisplayName("When issued receipt")
        void disSpenceReceipt() {
            customer.setName("Mathew Lake");
            Receipt receipt = cashierService.disSpenceReceipt(customer, 1000);

            assertEquals(receipt.getCustomerName(), customer.getName(), "name on receipt should be same as customer name");
        }


    }

    @Nested
    class SellProduct {

        @Test
        void sellProduct() {

            customer.setWalletBalance(2000000);
            double customerBalanceBefore = customer.getWalletBalance();

            Product productToSell = customerService.searchForProductByName("iPhone12");
            int countToBuy = 2;

            customerService.buyProduct(customer, productToSell, countToBuy);
            cashierService.sell(store.getSalesQueue());

            assertEquals(customer.getWalletBalance(), customerBalanceBefore - (productToSell.getPrice() * countToBuy));
        }


        @Test
        @DisplayName("When customer balance is low")
        void sellProductWhenNoBalance() {

            customer.setWalletBalance(20);
            double customerBalanceBfore = customer.getWalletBalance();

            Product productToSell = customerService.searchForProductByName("iPhone12");
            Integer countToBuy = 2;
            Integer availableBefore = store.getAvailableProducts().get(productToSell);
            try {
                customerService.buyProduct(customer, productToSell, countToBuy);
            } catch (InsufficientFundException e) {
                e.printStackTrace();
            }


            Integer availableAfter = store.getAvailableProducts().get(productToSell);
            assertEquals(availableAfter, availableBefore, "product should remain in available");
        }
    }

    @Nested
    class sellProductsThrows {


        @Test
        @DisplayName("When customer balance is low")
        void sellProductThrowsInsufficientFundsException() {


            customer.setWalletBalance(300000000.0);

            Product productToSell = customerService.searchForProductByName("iPhone12");
            customerService.buyProduct(customer, productToSell, 2);
            customer.setWalletBalance(40);


            assertThrows(InsufficientFundException.class,
                    () -> cashierService.sell(store.getSalesQueue()),
                    "should throw InsufficientFundException");
        }


        @Test
        @DisplayName("When customer balance is low")
        void sellProductThrowsProductNotInStoreException() {

            customer.setWalletBalance(40000000000.0);

            Product productToSell = customerService.searchForProductByName("iPhone12");
            customerService.buyProduct(customer, productToSell, 4);

            store.getAvailableProducts().replace(productToSell, store.getAvailableProducts().get(productToSell), 0);

            assertThrows(ProductOutOfStockException.class, () -> cashierService.sell(store.getSalesQueue()),
                    "should throw ProductNotInStoreException");
        }

    }

}