package com.migrosone.couriertracking.request.courier;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.migrosone.couriertracking.dto.location.PointDTO;
import com.migrosone.couriertracking.entity.courier.CourierGeoSignal;
import lombok.Data;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
public class SaveCourierGeoSignalRequest {

    private Long courierId;
    private PointDTO location;
    private LocalDateTime time;

    @JsonIgnore
    public CourierGeoSignal getCourierGeoSignal() {
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(
                new Coordinate(
                        location.getLongitude(),
                        location.getLatitude()
                )
        );

        CourierGeoSignal courierGeoSignal = new CourierGeoSignal();
        courierGeoSignal.setCourierId(courierId);
        courierGeoSignal.setLocation(point);
        courierGeoSignal.setCourierTime(time);

        return courierGeoSignal;
    }
}
