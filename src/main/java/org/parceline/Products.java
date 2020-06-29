package org.parceline;

import java.math.BigInteger;

public class Products {

    public static Product centralBin(int n) {
        BigInteger[] numerator = new BigInteger[n];
        BigInteger[] denominator = new BigInteger[n];
        for (int i = n + 1; i <= 2 * n; i++) {
            numerator[i - 1 - n] = BigInteger.valueOf(i);
        }
        for (int i = 1; i <= n; i++) {
            denominator[i - 1] = BigInteger.valueOf(i);
        }
        return new Product(numerator).divide(new Product(denominator));
    }
}
