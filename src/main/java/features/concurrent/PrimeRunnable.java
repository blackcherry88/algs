package features.concurrent;


/**
 * Uses a brute-force algorithm to determine if a given number is
 * prime or not.
 */
public class PrimeRunnable
        implements Runnable {

    /**
     * Number to evaluate for "primality".
     */
    private final long mPrimeCandidate;

    /**
     * Constructor initializes the fields.
     */
    public PrimeRunnable(long primeCandidate) {
        mPrimeCandidate = primeCandidate;
    }

    /**
     * This method provides a brute-force determination of whether
     * number @a n is prime.  Returns 0 if it is prime, -1 if operation
     * has been cancelled, or the smallest factor if it is not prime.
     */
    private long isComposite(long n) {
        if (n > 3) {
            long upBound = (long)Math.sqrt(n);
            for (long factor = 2; factor <= upBound; ++factor) {
                if (n / factor * factor == n) {
                    return factor;
                }
            }
        }

        return 0;
    }

    /**
     * Hook method that determines if a given number is prime.
     */
    public void run() {
        // Determine if mPrimeCandidate is prime or not.
        long smallestFactor = isComposite(mPrimeCandidate);
        System.out.println(mPrimeCandidate + " is " + (smallestFactor == 0L? "prime": "no prime"));
    }
}
