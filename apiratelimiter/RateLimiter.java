package interview;


/*
Token bucket algorithm implementation
 */
public class RateLimiter {

    private final long maxBucketSize;
    private final long refillRate;

    private double currentBucketSize;
    private long lastRefillTime;


    public RateLimiter(long maxBucketSize, long refillRate) {
        this.maxBucketSize = maxBucketSize;
        this.refillRate = refillRate;
        this.currentBucketSize = maxBucketSize;
        this.lastRefillTime = System.nanoTime();
    }

    public synchronized boolean allowRequest(int tokens) {
        refill();
        if (currentBucketSize > tokens) {
            currentBucketSize -= tokens;
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.nanoTime();
        double tokensToAdd = (now - lastRefillTime) * refillRate;
        currentBucketSize = Math.min(currentBucketSize+tokensToAdd, maxBucketSize);
        lastRefillTime = now;
    }

}
