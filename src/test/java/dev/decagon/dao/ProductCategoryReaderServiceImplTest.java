package dev.decagon.dao;

import dev.decagon.dao.ProductCategoryReaderServiceImpl;
import dev.decagon.entity.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProductCategoryReaderServiceImplTest {

    private Store store;
    ProductCategoryReaderServiceImpl productCategoryReaderService;

    @BeforeEach
    void setUp() {
        store =new Store();
        productCategoryReaderService=new ProductCategoryReaderServiceImpl(store.getProductCategory());
    }

    @Test
    void productCategoryReader() {
        int sizeBefore=store.getProductCategory().size();
        assertNotEquals(sizeBefore,productCategoryReaderService.productCategoryReader(
                "/Users/mac/Documents/decagon-assignments/week-two-sq012-CourageOghogho/e-store-app/src/main/resources/static/productCategory.csv").size());


    }
}