package com.migrosone.couriertracking.mapper.location;

import com.migrosone.couriertracking.dto.location.PointDTO;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PointMapper {

    public Point toPoint(PointDTO pointDTO) {
        if (pointDTO == null) {
            return null;
        }

        GeometryFactory geometryFactory = new GeometryFactory();
        return geometryFactory.createPoint(new org.locationtech.jts.geom.Coordinate(
                pointDTO.getLongitude(), pointDTO.getLatitude()));
    }

    public PointDTO toDto(Point point) {
        if (point == null) {
            return null;
        }

        PointDTO pointDTO = new PointDTO();
        pointDTO.setLatitude(point.getY());
        pointDTO.setLongitude(point.getX());

        return pointDTO;
    }
}
