package org.parceline;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

class ProductsTest {

    @Test
    void centralBin4() {
        Product p = Products.centralBin(4);
        Assertions.assertEquals(BigInteger.valueOf(70), p.compute());
    }

    @Test
    void centralBin5() {
        Product p = Products.centralBin(5);
        Assertions.assertEquals(BigInteger.valueOf(252), p.compute());
    }

    @Test
    void centralBin6() {
        Product p = Products.centralBin(6);
        Assertions.assertEquals(BigInteger.valueOf(924), p.compute());
    }

    @Test
    void centralBin7() {
        Product p = Products.centralBin(7);
        Assertions.assertEquals(BigInteger.valueOf(3432), p.compute());
    }

    @Test
    void centralBin8() {
        Product p = Products.centralBin(8);
        Assertions.assertEquals(BigInteger.valueOf(12870), p.compute());
    }
}