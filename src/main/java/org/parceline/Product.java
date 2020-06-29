package org.parceline;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class Product {

    private final BigInteger maxFactor;
    private final Sieve sieve;
    private final BigInteger[] factors;

    public Product(BigInteger[] factors) {
        this.factors = factors;
        this.maxFactor = Util.findMax(factors);
        this.sieve = Sieve.create(maxFactor.intValueExact() + 1);
    }

    public static Product create(List<BigInteger> factors) {
        return new Product(factors.toArray(new BigInteger[0]));
    }

    public static Product create(long... factors) {
        var result = new BigInteger[factors.length];
        for (int i = 0; i < factors.length; i++) {
            long factor = factors[i];
            result[i] = BigInteger.valueOf(factor);
        }
        return new Product(result);
    }

    private Product divide(BigInteger dividend) {
        if (dividend.equals(BigInteger.ZERO)) {
            throw new IllegalArgumentException("division by zero");
        }
        if (dividend.equals(BigInteger.ONE)) {
            return this;
        }
        for (int i = 0; i < factors.length; i++) {
            BigInteger factor = factors[i];
            BigInteger quotient = Division.tryDivide(factor, dividend);
            if (quotient != null) {
                if (quotient.equals(BigInteger.ONE)) {
                    BigInteger[] result = Arrays.copyOf(factors, factors.length - 1);
                    System.arraycopy(factors, i + 1, result, i, factors.length - i - 1);
                    return new Product(result);
                } else {
                    BigInteger[] result = Arrays.copyOf(factors, factors.length);
                    result[i] = quotient;
                    return new Product(result);
                }
            }
        }
        return null;
    }

    public Product divide(long... dividend) {
        return divide(create(dividend));
    }

    public Product divide(Product dividend) {
        Product d = this;
        for (BigInteger factor : dividend.factors) {
            Product q = d.divide(factor);
            if (q == null) {
                // assuming that the dividend doesn't have "large" prime factors,
                // so the sieve can be used
                List<BigInteger> primes = sieve.findPrimeFactors(factor);
                for (BigInteger prime : primes) {
                    Product q2 = d.divide(prime);
                    if (q2 == null) {
                        throw new IllegalArgumentException(prime + " does not divide " + this);
                    }
                    d = q2;
                }
            } else {
                d = q;
            }
        }
        return d;
    }

    @Override
    public String toString() {
        return Arrays.toString(factors);
    }

    public BigInteger compute() {
        BigInteger result = BigInteger.ONE;
        for (BigInteger factor : factors) {
            result = result.multiply(factor);
        }
        return result;
    }

    BigInteger[] factors() {
        return factors;
    }
}
