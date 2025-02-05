package com.migrosone.couriertracking.cache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.time.Duration;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RedisStringCacheTest {

    @InjectMocks
    public RedisStringCache cache;

    @Mock
    public StringRedisTemplate redisTemplate;
    @Mock
    public ValueOperations<String, String> valueOperations;

    @BeforeEach
    public void setUp() {
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    void givenCacheKeyValueDuration_whenPut_thenFinishWithoutError() {
        doNothing().when(valueOperations).set("key", "value", Duration.ofSeconds(1));

        cache.put("key", "value", Duration.ofSeconds(1));

        Mockito.verify(redisTemplate).opsForValue();
        Mockito.verify(valueOperations).set("key", "value", Duration.ofSeconds(1));
    }

    @Test
    void givenCacheKey_whenGet_thenReturnValue() {
        when(valueOperations.get("key")).thenReturn("value");

        final String result = cache.get("key");

        Assertions.assertEquals("value", result);
        Mockito.verify(redisTemplate).opsForValue();
        Mockito.verify(valueOperations).get("key");
    }
}