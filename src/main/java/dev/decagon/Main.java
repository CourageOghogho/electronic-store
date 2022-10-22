package dev.decagon;

import dev.decagon.dao.ProductCategoryReaderService;
import dev.decagon.dao.ProductCategoryReaderServiceImpl;
import dev.decagon.dao.ProductReaderService;
import dev.decagon.dao.ProductReaderServiceImpl;
import dev.decagon.entity.Customer;
import dev.decagon.entity.Product;
import dev.decagon.entity.Staff;
import dev.decagon.entity.Store;
import dev.decagon.enums.Role;
import dev.decagon.service.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        //Store Initializer
        Store store=new Store();
        store.setName("Courage Store");
        System.out.println("Welome to "+store.getName());

        StoreServiceImpl storeService=new StoreServiceImpl(store);

        String categoryPath="/Users/mac/Documents/decagon-assignment" +
                "s/week-three-sq012-CourageOghogho/e-store-app/src/main/resour" +
                "ces/static/productCategory.csv";

        String productPath="/Users/mac/Documents/decagon-assignments/week-three-sq012-" +
                "CourageOghogho/e-store-app/src/main/resources/static/productsAvailable.csv";

        ProductCategoryReaderService categoryReaderService=new ProductCategoryReaderServiceImpl(store.getProductCategory());
        ProductReaderService readerService=new ProductReaderServiceImpl(store.getAvailableProducts());

        categoryReaderService.productCategoryReader(categoryPath);
        readerService.productReader(productPath);


        System.out.println(store.getAvailableProducts().toString());




        Customer customer1=new Customer();
        customer1.setWalletBalance(2000000);
        customer1.setName("William");

        Customer customer2=new Customer();
        customer2.setWalletBalance(50000000);
        customer2.setName("Sam");

        Customer customer3=new Customer();
        customer3.setWalletBalance(100000000);
        customer3.setName("Joy");

        Customer customer4=new Customer();
        customer4.setWalletBalance(10000000);
        customer4.setName("Greg");






        CustomerService customerService =new CustomerServiceImpl(store);



        Product product1= customerService.searchForProductByName("iPhone12");
        //customerService.buyProduct(customer1,product1,2);





        Product product2= customerService.searchForProductByName("lotion");
        Product product3= customerService.searchForProductByName("Morning Star powder");


        CashierService cashierService=new CashierServiceImpl(store);

            List<BuyParallel> parallelBuy=new ArrayList<>();

           BuyParallel buyParallel1=new BuyParallel(customerService,customer1,product2,2);
        parallelBuy.add(buyParallel1);
           //buyParallel1.start();
           BuyParallel buyParallel2=new BuyParallel(customerService,customer2,product2,1);
        parallelBuy.add(buyParallel2);
           //buyParallel2.start();


           BuyParallel buyParallel3=new BuyParallel(customerService,customer3,product2,3);
           //buyParallel3.start();
        parallelBuy.add(buyParallel3);

        for(int i=0;i<parallelBuy.size();i++){
            BuyParallel buy=parallelBuy.get(i);
            buy.start();
            Thread sellThread=new Thread(new ParallelSales(cashierService, buy.getCustomer()){
                @Override
                public void run() {
                    try {
                        buy.join();
                        buy.getCustomer().getReceipts().forEach(System.out::println);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            sellThread.start();



        }




       // sellThread1.start();


//        Thread sellThread2=new Thread(new ParallelSales(cashierService,customer2));
//        sellThread2.start();
//
//
//        Thread sellThread3=new Thread(new ParallelSales(cashierService,customer3));
//        sellThread3.start();



//        System.out.println("===============1111===================");
//        customer1.getReceipts().forEach(System.out::println);
//        System.out.println("===============2222===================");
//        customer2.getReceipts().forEach(System.out::println);
//        System.out.println("===============3333===================");
//        customer3.getReceipts().forEach(System.out::println);




    }
}