package org.parceline;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Sieve {

    private final BigInteger[] primes;

    Sieve(BigInteger[] primes) {
        this.primes = primes;
    }

    static Sieve create(int max) {
        boolean[] prime = new boolean[max + 1];
        Arrays.fill(prime, true);
        long stop = Math.round(Math.sqrt(max));
        for (int n = 2; n <= stop; n++) {
            if (prime[n]) {
                for (int i = n * n; i <= max; i += n) {
                    prime[i] = false;
                }
            }
        }
        return new Sieve(toInts(prime));
    }

    List<BigInteger> factor(BigInteger n) {
        List<BigInteger> result = new ArrayList<>();
        for (BigInteger prime : primes) {
            BigInteger quotient;
            while ((quotient = tryDivide(n, prime)) != null) {
                result.add(prime);
                n = quotient;
            }
        }
        return result;
    }

    private BigInteger tryDivide(BigInteger n, BigInteger prime) {
        BigInteger[] qr = n.divideAndRemainder(prime);
        BigInteger quotient = qr[0];
        BigInteger remainder = qr[1];
        if (remainder.equals(BigInteger.ZERO)) {
            return quotient;
        }
        return null;
    }

    private static BigInteger[] toInts(boolean[] prime) {
        int numPrimes = 0;
        for (int n = 2; n < prime.length; n++) {
            if (prime[n]) {
                numPrimes++;
            }
        }
        BigInteger[] primes = new BigInteger[numPrimes];
        int pos = 0;
        for (int n = 2; n < prime.length; n++) {
            if (prime[n]) {
                primes[pos++] = BigInteger.valueOf(n);
            }
        }
        return primes;
    }

    public BigInteger[] primes() {
        return primes;
    }
}