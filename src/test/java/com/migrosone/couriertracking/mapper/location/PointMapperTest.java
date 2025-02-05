package com.migrosone.couriertracking.mapper.location;

import com.migrosone.couriertracking.dto.location.PointDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class PointMapperTest {

    @InjectMocks
    public PointMapper mapper;

    @Test
    void givenNullPointDTO_whenToPoint_thenReturnNullPoint() {
        assertNull(mapper.toPoint(null));
    }

    @Test
    void givenPointDTO_whenToPoint_thenReturnPoint() {
        final PointDTO pointDTO = new PointDTO();
        pointDTO.setLatitude(40.0);
        pointDTO.setLongitude(29.0);

        final Point result = mapper.toPoint(pointDTO);

        assertEquals(40.0, result.getY());
        assertEquals(29.0, result.getX());
    }

    @Test
    void givenNullPoint_whenToDto_thenReturnNullPointDto() {
        assertNull(mapper.toDto(null));
    }

    @Test
    void givenPoint_whenToDto_thenReturnPointDto() {
        final GeometryFactory geometryFactory = new GeometryFactory();
        final Point point = geometryFactory.createPoint(new org.locationtech.jts.geom.Coordinate(29.0, 40.0));

        final PointDTO result = mapper.toDto(point);

        assertEquals(40.0, result.getLatitude());
        assertEquals(29.0, result.getLongitude());
    }
}