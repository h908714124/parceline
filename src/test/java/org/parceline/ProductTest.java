package org.parceline;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

class ProductTest {

    @Test
    void create() {
        Product p = Product.create(8, 7, 6, 5).divide(4, 3, 2, 1);
        Assertions.assertEquals(BigInteger.valueOf(70), p.compute());
    }
}