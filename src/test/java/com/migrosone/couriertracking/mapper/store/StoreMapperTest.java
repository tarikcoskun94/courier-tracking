package com.migrosone.couriertracking.mapper.store;

import com.migrosone.couriertracking.dto.location.PointDTO;
import com.migrosone.couriertracking.dto.store.StoreDTO;
import com.migrosone.couriertracking.entity.EmbeddedCreationAudit;
import com.migrosone.couriertracking.entity.EmbeddedUpdateAudit;
import com.migrosone.couriertracking.entity.store.Store;
import com.migrosone.couriertracking.mapper.location.PointMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StoreMapperTest {

    @InjectMocks
    private StoreMapper mapper;

    @Spy
    private PointMapper pointMapper;

    @Test
    void givenNullStoreDTO_whenToEntity_thenReturnNullStoreEntity() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    void givenStoreDTO_whenToEntity_thenReturnStoreEntity() {
        final PointDTO pointDTO = new PointDTO();
        pointDTO.setLatitude(40.0);
        pointDTO.setLongitude(29.0);

        final StoreDTO dto = new StoreDTO();
        dto.setId(1L);
        dto.setName("Ataşehir Migros MMM");
        dto.setLocation(pointDTO);

        final Store result = mapper.toEntity(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Ataşehir Migros MMM", result.getName());
        assertEquals(40.0, result.getLocation().getY());
        assertEquals(29.0, result.getLocation().getX());
    }

    @Test
    void givenNullStoreEntity_whenToDto_thenReturnNullStoreDto() {
        assertNull(mapper.toDto(null));
    }

    @Test
    void givenStoreEntity_whenToDto_thenReturnStoreDto() {
        final GeometryFactory geometryFactory = new GeometryFactory();
        final Point point = geometryFactory.createPoint(new org.locationtech.jts.geom.Coordinate(29.0, 40.0));

        final EmbeddedCreationAudit creationAudit = new EmbeddedCreationAudit();
        creationAudit.setCreationDate(LocalDateTime.of(2025, 1, 1, 0, 0, 0, 0));
        creationAudit.setCreatorUser("SYSTEM_CR");

        final EmbeddedUpdateAudit updateAudit = new EmbeddedUpdateAudit();
        updateAudit.setUpdateDate(LocalDateTime.of(2026, 1, 1, 0, 0, 0, 0));
        updateAudit.setUpdaterUser("SYSTEM_UP");

        final Store store = new Store();
        store.setId(1L);
        store.setName("Ataşehir Migros MMM");
        store.setLocation(point);
        store.setCreationAudit(creationAudit);
        store.setUpdateAudit(updateAudit);

        final StoreDTO result = mapper.toDto(store);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Ataşehir Migros MMM", result.getName());
        assertNotNull(result.getLocation());
        assertEquals(40.0, result.getLocation().getLatitude());
        assertEquals(29.0, result.getLocation().getLongitude());
        assertEquals("SYSTEM_CR", result.getCreatorUser());
        assertEquals(LocalDateTime.of(2025, 1, 1, 0, 0, 0, 0), result.getCreationDate());
        assertEquals("SYSTEM_UP", result.getUpdaterUser());
        assertEquals(LocalDateTime.of(2026, 1, 1, 0, 0, 0, 0), result.getUpdateDate());
    }
}