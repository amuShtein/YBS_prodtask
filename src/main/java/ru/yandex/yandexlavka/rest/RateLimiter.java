package ru.yandex.yandexlavka.rest;


import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.yandexlavka.rest.exceptions.TooManyConnectionsException;

import java.time.Duration;

@Component
public class RateLimiter {
    private final Bucket bucket;

    public RateLimiter() {
        Bandwidth limit = Bandwidth.classic(10, Refill.intervally(10, Duration.ofSeconds(1)));
        this.bucket = Bucket.builder().addLimit(limit).build();
    }

    public void countConnection() {
        if(!bucket.tryConsume(1))
            throw new TooManyConnectionsException();
    }
}
