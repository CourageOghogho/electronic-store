package dev.decagon.dao;

import dev.decagon.dao.ProductReaderServiceImpl;
import dev.decagon.entity.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProductReaderServiceImplTest {

    private Store store;
    ProductReaderServiceImpl productReaderService;

    @BeforeEach
    void setUp() {
        store =new Store();
        productReaderService=new ProductReaderServiceImpl(store.getAvailableProducts());
    }

    @Test
    void productReader() {
        int sizeBefore=store.getAvailableProducts().size();
        productReaderService.productReader(
                "/Users/mac/Documents/decagon-assignments/week-two-sq012-CourageOghogho/e-store-app/src/main/resources/static/productsAvailable.csv");

        assertNotEquals(sizeBefore,store.getAvailableProducts().size());
    }
}