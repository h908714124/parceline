package org.parceline;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SieveTest {

    @Test
    void testSieve() {
        Sieve sieve = Sieve.create(97); // primes not greater than 97
        for (BigInteger p : sieve.primes()) {
            assertTrue(p.isProbablePrime(100));
        }
        List<BigInteger> primes = Arrays.asList(sieve.primes());
        System.out.println(primes);
        assertEquals(25, new HashSet<>(primes).size());
    }

    @Test
    void testFactor() {
        Sieve sieve = Sieve.create(55692); // primes not greater than 55692
        List<BigInteger> result = sieve.findPrimeFactors(BigInteger.valueOf(13).multiply(BigInteger.valueOf(17).multiply(BigInteger.valueOf(252))));
        for (BigInteger p : result) {
            assertTrue(p.isProbablePrime(100));
        }
        BigInteger product = Product.create(result).compute();
        Assertions.assertEquals(BigInteger.valueOf(13 * 17 * 252), product);
    }
}