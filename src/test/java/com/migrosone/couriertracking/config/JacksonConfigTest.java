package com.migrosone.couriertracking.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class JacksonConfigTest {

    @InjectMocks
    public JacksonConfig config;

    @Test
    void whenObjectMapper_thenReturnAuditorString() {
        assertNotNull(config.objectMapper());
    }
}