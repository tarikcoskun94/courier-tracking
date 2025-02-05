package com.migrosone.couriertracking.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AuditorConfigTest {

    @InjectMocks
    public AuditorConfig config;

    @Test
    void whenAuditorProvider_thenReturnAuditorString() {
        assertEquals("SYSTEM", config.auditorProvider().getCurrentAuditor().orElseThrow());
    }
}