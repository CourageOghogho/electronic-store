package dev.decagon.dao;

import dev.decagon.entity.Product;
import dev.decagon.exception.IlegalFilePathException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class ProductReaderServiceImpl implements ProductReaderService {

    private Hashtable<Product,Integer> availableProducts;
    private BufferedReader reader;


    public ProductReaderServiceImpl(Hashtable<Product, Integer> availableProducts) {
        this.availableProducts = availableProducts;
    }

    @Override
    public Hashtable<Product,Integer> productReader(String filePath){

        if(filePath==null || filePath=="")
            throw new IlegalFilePathException("String filepath cannot be empty or null");

        String line="";
        try {
            reader=new BufferedReader(new FileReader(filePath));
            line=reader.readLine();
            while (line!=null){
                String[] value=line.split(",");
                Product product=new Product();
                product.setId(Integer.parseInt(value[0]));
                product.setName(value[1]);
                product.setPrice(Float.parseFloat(value[2]));
                product.setCategory(value[3]);
                availableProducts.put(product, Integer.valueOf(value[4]));
                line=reader.readLine();
            }


        } catch (IOException fo){
            fo.printStackTrace();
        }

        return availableProducts;

    }

}
