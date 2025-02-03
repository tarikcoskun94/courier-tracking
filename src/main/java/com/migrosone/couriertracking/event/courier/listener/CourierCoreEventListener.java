package com.migrosone.couriertracking.event.courier.listener;

import com.migrosone.couriertracking.entity.courier.Courier;
import com.migrosone.couriertracking.event.courier.CourierDriveFinishEvent;
import com.migrosone.couriertracking.service.courier.CourierCoreService;
import com.migrosone.couriertracking.service.courier.CourierGeoSignalCoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CourierCoreEventListener {

    private final CourierCoreService courierCoreService;
    private final CourierGeoSignalCoreService courierGeoSignalCoreService;

    @EventListener
    public void handleCourierDriveFinishEvent(CourierDriveFinishEvent event) {
        Courier foundCourier = courierCoreService.findById(event.getCourierId())
                .orElseThrow(() -> new IllegalStateException("Not found the courier"));

        BigDecimal travelDistance = courierGeoSignalCoreService.calculateTravel(event.getFinishedDrive());
        foundCourier.addTotalDistance(travelDistance);

        courierCoreService.save(foundCourier);
    }
}
