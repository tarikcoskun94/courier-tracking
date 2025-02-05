package com.migrosone.couriertracking.mapper.courier;

import com.migrosone.couriertracking.dto.courier.CourierDTO;
import com.migrosone.couriertracking.entity.EmbeddedCreationAudit;
import com.migrosone.couriertracking.entity.EmbeddedUpdateAudit;
import com.migrosone.couriertracking.entity.courier.Courier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class CourierMapperTest {

    @InjectMocks
    public CourierMapper mapper;

    @Test
    void givenNullCourierDTO_whenToEntity_thenReturnNullCourierEntity() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    void givenCourierDTO_whenToEntity_thenReturnCourierEntity() {
        final CourierDTO dto = new CourierDTO();
        dto.setId(100L);
        dto.setFirstName("Ali");
        dto.setLastName("MELEK");
        dto.setTotalDistance(BigDecimal.ONE.setScale(2, RoundingMode.HALF_UP));
        dto.setCreatorUser("CR_USER");
        dto.setCreationDate(LocalDateTime.of(2025, 1, 1, 0, 0, 0, 0));
        dto.setUpdaterUser("UP_USER2");
        dto.setUpdateDate(LocalDateTime.of(2026, 1, 1, 0, 0, 0, 0));

        final Courier result = mapper.toEntity(dto);

        assertEquals(100L, result.getId());
        assertEquals("Ali", result.getFirstName());
        assertEquals("MELEK", result.getLastName());
        assertEquals(BigDecimal.ONE.setScale(2, RoundingMode.HALF_UP), result.getTotalDistance());
    }

    @Test
    void givenNullCourierEntity_whenToDto_thenReturnNullCourierDTO() {
        assertNull(mapper.toDto(null));
    }

    @Test
    void givenCourierEntity_whenToDto_thenReturnCourierDTO() {
        final Courier entity = new Courier();
        entity.setId(200L);
        entity.setFirstName("Veli");
        entity.setLastName("CAN");
        entity.setTotalDistance(BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP));

        final EmbeddedCreationAudit creationAudit = new EmbeddedCreationAudit();
        creationAudit.setCreatorUser("CREATOR_USER");
        creationAudit.setCreationDate(LocalDateTime.of(2023, 3, 1, 10, 15, 30));
        entity.setCreationAudit(creationAudit);

        final EmbeddedUpdateAudit updateAudit = new EmbeddedUpdateAudit();
        updateAudit.setUpdaterUser("UPDATER_USER");
        updateAudit.setUpdateDate(LocalDateTime.of(2024, 4, 2, 11, 20, 45));
        entity.setUpdateAudit(updateAudit);

        final CourierDTO result = mapper.toDto(entity);

        assertEquals(200L, result.getId());
        assertEquals("Veli", result.getFirstName());
        assertEquals("CAN", result.getLastName());
        assertEquals(BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP), result.getTotalDistance());
        assertEquals("CREATOR_USER", result.getCreatorUser());
        assertEquals(LocalDateTime.of(2023, 3, 1, 10, 15, 30), result.getCreationDate());
        assertEquals("UPDATER_USER", result.getUpdaterUser());
        assertEquals(LocalDateTime.of(2024, 4, 2, 11, 20, 45), result.getUpdateDate());
    }
}