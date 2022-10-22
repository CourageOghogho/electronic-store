package dev.decagon.util;

import dev.decagon.entity.Customer;

public class CustomerComparatorByCart implements java.util.Comparator<Customer> {
    @Override
    public int compare(Customer customer1, Customer customer2) {
        return customer2.getCart().size()-customer1.getCart().size();
    }
}
