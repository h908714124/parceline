package org.parceline;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class Product {

    private final BigInteger[] factors;

    public Product(BigInteger[] factors) {
        this.factors = factors;
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

    public Product divide(BigInteger dividend) {
        if (dividend.equals(BigInteger.ZERO)) {
            throw new IllegalArgumentException("division by zero");
        }
        if (dividend.equals(BigInteger.ONE)) {
            return this;
        }
        for (int i = 0; i < factors.length; i++) {
            BigInteger factor = factors[i];
            BigInteger[] qr = factor.divideAndRemainder(dividend);
            BigInteger quotient = qr[0];
            BigInteger remainder = qr[1];
            if (remainder.equals(BigInteger.ZERO)) {
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
        throw new IllegalArgumentException(dividend + " does not divide " + this);
    }

    public Product divide(long... dividend) {
        return divide(create(dividend));
    }

    public Product divide(Product dividend) {
        Product d = this;
        for (BigInteger factor : dividend.factors) {
            d = d.divide(factor);
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
}
