package org.parceline;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Sieve {

    private final BigInteger max;
    private final BigInteger[] primes;

    private Sieve(int max, BigInteger[] primes) {
        this.max = BigInteger.valueOf(max);
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
        return new Sieve(max, toInts(prime));
    }

    List<BigInteger> findPrimeFactors(BigInteger n) {
        if (max.subtract(n).signum() == -1) {
            throw new IllegalArgumentException(n + " is too large, max is " + max);
        }
        List<BigInteger> result = new ArrayList<>();
        for (BigInteger prime : primes) {
            BigInteger quotient;
            while ((quotient = Division.tryDivide(n, prime)) != null) {
                result.add(prime);
                n = quotient;
            }
        }
        return result;
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