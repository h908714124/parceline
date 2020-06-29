package org.parceline;

import java.math.BigInteger;

class Division {

    static BigInteger tryDivide(BigInteger n, BigInteger prime) {
        BigInteger[] qr = n.divideAndRemainder(prime);
        BigInteger quotient = qr[0];
        BigInteger remainder = qr[1];
        if (remainder.equals(BigInteger.ZERO)) {
            return quotient;
        }
        return null;
    }
}
