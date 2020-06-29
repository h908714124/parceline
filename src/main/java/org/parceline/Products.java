package org.parceline;

import java.math.BigInteger;

public class Products {

    public static Product centralBin(int n) {
        BigInteger[] numerator = new BigInteger[n];
        BigInteger[] denominator = new BigInteger[n];
        for (int i = 1; i <= n; i++) {
            numerator[i - 1] = BigInteger.valueOf(i + n);
            denominator[i - 1] = BigInteger.valueOf(i);
        }
        return new Product(numerator).divide(new Product(denominator));
    }
}
