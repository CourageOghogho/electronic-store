package dev.decagon.service;

import dev.decagon.entity.Applicant;
import dev.decagon.entity.Product;
import dev.decagon.entity.Staff;

public interface StoreService {
    public boolean createProductCategory(String category);
    public boolean deleteProductCategory(String category);
    public boolean addProduct(Product product,Integer count);
    public Product addNewProductToAvailability(Product product);
    public  Product deleteProductFromAvailability(Product product);
    public  boolean removeProduct(Product product, Integer count);

    public String addApplicantToPool(Applicant applicant);
    public String addEmployee(Staff staff);

}
