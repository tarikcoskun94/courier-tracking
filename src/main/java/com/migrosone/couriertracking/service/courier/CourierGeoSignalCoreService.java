package com.migrosone.couriertracking.service.courier;

import com.migrosone.couriertracking.entity.courier.CourierGeoSignal;

import java.util.List;
import java.util.Optional;

public interface CourierGeoSignalCoreService {

    List<CourierGeoSignal> findAll();

    Optional<CourierGeoSignal> findById(Long id);

    CourierGeoSignal save(CourierGeoSignal geoSignal);

    List<CourierGeoSignal> saveAll(List<CourierGeoSignal> geoSignals);
}
