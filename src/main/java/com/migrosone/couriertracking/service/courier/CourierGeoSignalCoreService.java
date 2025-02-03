package com.migrosone.couriertracking.service.courier;

import com.migrosone.couriertracking.entity.courier.CourierDrive;
import com.migrosone.couriertracking.entity.courier.CourierGeoSignal;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CourierGeoSignalCoreService {

    List<CourierGeoSignal> findAll();

    List<CourierGeoSignal> findAllById(Iterable<Long> ids);

    Optional<CourierGeoSignal> findById(Long id);

    BigDecimal calculateTravel(CourierDrive finishedDrive);

    CourierGeoSignal save(CourierGeoSignal geoSignal);

    List<CourierGeoSignal> saveAll(Iterable<CourierGeoSignal> geoSignals);
}
