package dev.raj.paymentapi.Services;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimitService {

    private final Bucket reportsBucket;
    private final Bucket TransactionBucket;
    public RateLimitService() {
        this.reportsBucket= createBucket(5, Duration.ofMinutes(1));
        this.TransactionBucket= createBucket(5, Duration.ofMinutes(1));
    }

    private Bucket createBucket(int size, Duration time) {
        return Bucket4j.builder()
                .addLimit(Bandwidth.classic(size, Refill.intervally(size, time)))
                .build();
    }
    public boolean tryConsumeReports() {
        return reportsBucket.tryConsume(1);
    }
    public boolean tryConsumeTransaction() {
        return TransactionBucket.tryConsume(1);
    }
}
