package org.parceline;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.math.BigInteger;
import java.util.List;

@TestInstance(Lifecycle.PER_CLASS)
class ProductsTest {

    private final Sieve sieve = Sieve.create(1000);

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

    @Test
    void centralBin467() {
        Product p = Products.centralBin(467);
        BigInteger[] qr = p.compute().divideAndRemainder(BigInteger.valueOf(929));
        Assertions.assertEquals(BigInteger.ZERO, qr[1]);
    }

    @Test
    void centralBin468() {
        Product p = Products.centralBin(468);
        BigInteger[] qr = p.compute().divideAndRemainder(BigInteger.valueOf(929));
        Assertions.assertEquals(BigInteger.ZERO, qr[1]);
    }

    @Test
    void centralBinLoop() {
        for (int i = 1; i < 100; i++) {
            Product p = Products.centralBin(i);
            BigInteger f = findLargestPrimeFactor(p);
            Assertions.assertEquals(-1, BigInteger.valueOf(i).subtract(f).signum());
        }
    }

    private BigInteger findLargestPrimeFactor(Product p) {
        BigInteger result = BigInteger.ZERO;
        for (BigInteger factor : p.factors()) {
            List<BigInteger> primes = sieve.findPrimeFactors(factor);
            for (BigInteger prime : primes) {
                result = result.max(prime);
            }
        }
        return result;
    }
}