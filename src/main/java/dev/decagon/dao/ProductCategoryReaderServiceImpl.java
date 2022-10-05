package dev.decagon.dao;

import dev.decagon.exception.IlegalFilePathException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class ProductCategoryReaderServiceImpl implements ProductCategoryReaderService {

    private Set<String> productCategory;

    private BufferedReader reader;


    public ProductCategoryReaderServiceImpl(Set<String> productCategory) {
        this.productCategory = productCategory;

    }

   // @Override
    public Set<String> productCategoryReader(String filePath){
        if(filePath==null || filePath=="")
            throw new IlegalFilePathException("String filepath cannot be empty or null");
        String line="";
        try {
            BufferedReader reader=new BufferedReader(new FileReader(filePath));
            line=reader.readLine();
            while (line!=null){
                productCategory.add(line);
                line=reader.readLine();
            }


        } catch (IOException fo){
            fo.printStackTrace();
        }

        return productCategory;
    }

}
