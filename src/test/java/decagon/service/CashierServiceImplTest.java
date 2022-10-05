package decagon.service;

import dev.decagon.entity.*;
import dev.decagon.enums.Role;
import dev.decagon.exception.InsufficientFundException;
import dev.decagon.exception.ProductAlreadyExistsException;
import dev.decagon.exception.ProductOutOfStockException;
import dev.decagon.service.CashierServiceImpl;
import dev.decagon.service.StoreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashierServiceImplTest {

    @Nested
    class ReceiptTest{
            Store store=new Store();
            StoreServiceImpl storeService=new StoreServiceImpl(store);
            Product cup = new Product(10, "One-World glass cup", 2500.50f);
            CashierServiceImpl cashierService;


        @BeforeEach
        void setUp() {
            store.setName("Courage Store");
            Staff cashier=new Staff();
            cashier.setRole(Role.CASHIER);
            cashierService=CashierServiceImpl.cashierService(cashier,store.getAvailableProducts());
        }

        @Test
       @DisplayName("When issued receipt")
       void disSpenceReceipt() {
            storeService.createProductCategory("CUP");


           storeService.addNewProductToAvailability(cup);
           cup.setCategory("CUP");
           Customer customer=new Customer();
           customer.setName("Okorie");
           Receipt receipt=cashierService.disSpenceReceipt(customer,cup,3);

           assertEquals(receipt.getCustomerName(),customer.getName(),"name on receipt should be same as customer name");
       }



        @Test
        @DisplayName("When receipt is ready")
        void disSpenceReceiptItemCount() {
            Store store=new Store();
            store.setName("Courage Store");
            Staff cashier=new Staff();
            cashier.setRole(Role.CASHIER);
            CashierServiceImpl cashierService=CashierServiceImpl.cashierService(cashier,store.getAvailableProducts());

            StoreServiceImpl storeService=new StoreServiceImpl(store);

            Product cup = new Product(10, "One-World glass cup", 2500.50f);
            try{
                storeService.createProductCategory("CUP");
            }catch (ProductAlreadyExistsException pe){
                pe.printStackTrace();
            }

            storeService.addNewProductToAvailability(cup);
            cup.setCategory("CUP");
            Customer customer=new Customer();
            customer.setName("Okorie");
            int count=3;
            Receipt receipt=cashierService.disSpenceReceipt(customer,cup,count);

            assertEquals(receipt.getNumberOfItem(),count,"number of item on receipt should be same as count");
        }


   }
   @Nested
   class  SellProduct{
       @Test
       void sellProduct() {

           Store store=new Store();
           store.setName("Courage Store");
           Staff cashier=new Staff();
           cashier.setRole(Role.CASHIER);
           CashierServiceImpl cashierService=CashierServiceImpl.cashierService(cashier,store.getAvailableProducts());

           StoreServiceImpl storeService=new StoreServiceImpl(store);

           Product cup = new Product(10, "One-World glass cup", 2500.50f);
           try{
               storeService.createProductCategory("CUP");
           }catch (ProductAlreadyExistsException pe){
               pe.printStackTrace();
           }

           storeService.addNewProductToAvailability(cup);
           cup.setCategory("CUP");
           Customer customer=new Customer();
           customer.setName("Okorie");
           customer.setWalletBalance(2000000);
           double customerBalanceBfore=customer.getWalletBalance();

           Receipt receipt;
                 try {
                     receipt=cashierService.sellProduct(customer,cup,1);
                 }catch (InsufficientFundException ife){
                     ife.printStackTrace();
                 }
            assertEquals(customer.getWalletBalance(), customerBalanceBfore-cup.getPrice());
       }


       @Test
       @DisplayName("When customer balance is low")
       void sellProductWhenNoBalance() {

           Store store=new Store();
           store.setName("Courage Store");
           Staff cashier=new Staff();
           cashier.setRole(Role.CASHIER);
           CashierServiceImpl cashierService=CashierServiceImpl.cashierService(cashier,store.getAvailableProducts());

           StoreServiceImpl storeService=new StoreServiceImpl(store);

           Product cup = new Product(10, "One-World glass cup", 2500.50f);
           try{
               storeService.createProductCategory("CUP");
           }catch (ProductAlreadyExistsException pe){
               pe.printStackTrace();
           }

           storeService.addNewProductToAvailability(cup);
           cup.setCategory("CUP");
           Customer customer=new Customer();
           customer.setName("Okorie");
           customer.setWalletBalance(0.0);
           double customerBalanceBfore=customer.getWalletBalance();
           Integer availableBefore=store.getAvailableProducts().get(cup);
           Receipt receipt;
           try {
               receipt=cashierService.sellProduct(customer,cup,1);
           }catch (InsufficientFundException ife){
               ife.printStackTrace();
           }
           Integer availableAfter=store.getAvailableProducts().get(cup);
           assertEquals(availableAfter,availableBefore,"product should remain in available");
       }
   }
   @Nested
   class sellProductsThrows{

       @Test
       @DisplayName("When customer balance is low")
       void sellProductThrowsInsufficientFundsException() {

           Store store=new Store();
           store.setName("Courage Store");
           Staff cashier=new Staff();
           cashier.setRole(Role.CASHIER);
           CashierServiceImpl cashierService=CashierServiceImpl.cashierService(cashier,store.getAvailableProducts());

           StoreServiceImpl storeService=new StoreServiceImpl(store);

           Product cup = new Product(10, "One-World glass cup", 2500.50f);
               storeService.createProductCategory("CUP");

           storeService.addNewProductToAvailability(cup);
           cup.setCategory("CUP");
           Customer customer=new Customer();
           customer.setName("Okorie");
           customer.setWalletBalance(0.0);

           assertThrows(InsufficientFundException.class,()->cashierService.sellProduct(customer,cup,1),
                   "should throw InsufficientFundException");
       }


       @Test
       @DisplayName("When customer balance is low")
       void sellProductThrowsProductNotInStoreException() {

           Store store=new Store();
           store.setName("Courage Store");
           Staff cashier=new Staff();
           cashier.setRole(Role.CASHIER);
           CashierServiceImpl cashierService=CashierServiceImpl.cashierService(cashier,store.getAvailableProducts());

           StoreServiceImpl storeService=new StoreServiceImpl(store);

           Product cup = new Product(10, "One-World glass cup", 2500.50f);
           storeService.createProductCategory("CUP");

           storeService.addNewProductToAvailability(cup);
           cup.setCategory("CUP");
           Customer customer=new Customer();
           customer.setName("Okorie");
           customer.setWalletBalance(200000.0);

           assertThrows(ProductOutOfStockException.class,()->cashierService.sellProduct(customer,cup,2),
                   "should throw ProductNotInStoreException");
       }

   }
}