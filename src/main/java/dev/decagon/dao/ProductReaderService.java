package dev.decagon.dao;

import dev.decagon.entity.Product;

import java.util.Hashtable;

public interface ProductReaderService {

    public Hashtable<Product,Integer> productReader(String filePath);
}
