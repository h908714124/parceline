package org.parceline;

import java.math.BigInteger;

class Util {

    static BigInteger findMax(BigInteger[] factors) {
        BigInteger result = BigInteger.ZERO;
        for (BigInteger factor : factors) {
            result = factor.max(result);
        }
        return result;
    }
}
