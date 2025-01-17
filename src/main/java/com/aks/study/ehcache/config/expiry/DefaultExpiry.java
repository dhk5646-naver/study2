package com.aks.study.ehcache.config.expiry;

import com.aks.study.ehcache.entity.Person;
import org.ehcache.ValueSupplier;
import org.ehcache.expiry.ExpiryPolicy;
import org.ehcache.xml.model.TimeUnit;

import java.time.Duration;
import java.util.function.Supplier;

public class DefaultExpiry implements ExpiryPolicy<String, Person> {

    private final static Duration DEFAULT_DURATION = Duration.ofMinutes(5); // 5분

    @Override
    public Duration getExpiryForCreation(String s, Person person) {
        return DEFAULT_DURATION;
    }

    @Override
    public Duration getExpiryForAccess(String s, Supplier<? extends Person> supplier) {
        return DEFAULT_DURATION;
    }

    @Override
    public Duration getExpiryForUpdate(String s, Supplier<? extends Person> supplier, Person person) {
        return DEFAULT_DURATION;
    }
}
