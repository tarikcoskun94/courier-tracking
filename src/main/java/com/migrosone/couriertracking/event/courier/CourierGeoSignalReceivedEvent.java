package com.migrosone.couriertracking.event.courier;

import com.migrosone.couriertracking.entity.courier.CourierGeoSignal;
import lombok.Getter;
import org.locationtech.jts.geom.Point;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class CourierGeoSignalReceivedEvent extends ApplicationEvent {

    private final CourierGeoSignal receivedGeoSignal;

    private CourierGeoSignalReceivedEvent(Object source, CourierGeoSignal receivedGeoSignal) {
        super(source);
        this.receivedGeoSignal = receivedGeoSignal;
    }

    public static CourierGeoSignalReceivedEvent of(Object source, CourierGeoSignal receivedGeoSignal) {
        return new CourierGeoSignalReceivedEvent(source, receivedGeoSignal);
    }

    public Point getLocation() {
        return receivedGeoSignal.getLocation();
    }

    public Long getCourierId() {
        return receivedGeoSignal.getCourierId();
    }

    public LocalDateTime getCourierTime() {
        return receivedGeoSignal.getCourierTime();
    }
}
