package dev.decagon.service;

import dev.decagon.entity.Applicant;
import dev.decagon.entity.Product;
import dev.decagon.entity.Staff;
import dev.decagon.entity.Store;
import dev.decagon.exception.NoSuchProductCategoryExistsException;
import dev.decagon.exception.NoSuchProductInAvailabilityException;
import dev.decagon.exception.ProductAlreadyExistsException;
import dev.decagon.exception.ProductOutOfStockException;

import java.util.Hashtable;
import java.util.Set;

public class StoreServiceImpl implements StoreService {

    private Set<String> productCategories;
    private Hashtable<Product,Integer> availableProducts=new Hashtable<>();
    private  Store store;

    public StoreServiceImpl(Store store) {

        this.productCategories = store.getProductCategory();
        this.availableProducts = store.getAvailableProducts();
        this.store=store;
    }

    @Override
    public boolean createProductCategory(String category)  {
        if(!productCategories.add(category)){
            throw new ProductAlreadyExistsException(category+" already exists");
        }
        return true;
    }

    @Override
    public boolean deleteProductCategory(String category)
            throws NoSuchProductCategoryExistsException {
        if(!productCategories.remove(category))
            throw new NoSuchProductCategoryExistsException(category+" does not exist");
        return false;
    }

    @Override
    public boolean addProduct(Product product, Integer count) {
        for(Product item:this.availableProducts.keySet()){
            if(item.getName().equals(product.getName())){
                Integer value=this.availableProducts.get(item);
                this.availableProducts.replace(item,value,value+count);
                return  true;
            }
        }
        return false;
    }

    @Override
    public Product addNewProductToAvailability(Product product) {
        if(!this.addProduct(product,1))
            this.availableProducts.put(product,1);
        return product;
    }

    @Override
    public Product deleteProductFromAvailability(Product product)  {
        if(this.availableProducts.remove(product)>0)
            return product;
        throw new NoSuchProductInAvailabilityException(product.getName()+" is not in the availability list");
    }

    @Override
    public boolean removeProduct(Product product, Integer count){

        for(Product item:this.availableProducts.keySet()){
            if(item.getName().equals(product.getName())){
                Integer value=this.availableProducts.get(item);
                if(value>=count){
                    this.availableProducts.replace(item,value,value-count);
                    return  true;
                }
                throw new ProductOutOfStockException(item.getName()+" not in stock");
            }
        }throw new NoSuchProductInAvailabilityException(product.getName()+" is not in the availability list");
    }

    @Override
    public String addApplicantToPool(Applicant applicant) {
        store.getApplicantsPool().add(applicant);
        return applicant.getApplicationID();

    }

    @Override
    public String addEmployee(Staff staff) {
         store.getEmployees().add(staff);
         return staff.getEmploymentID();
    }
}
