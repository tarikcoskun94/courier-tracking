package com.migrosone.couriertracking.service.courier.impl;

import com.migrosone.couriertracking.entity.courier.Courier;
import com.migrosone.couriertracking.entity.courier.CourierGeoSignal;
import com.migrosone.couriertracking.mapper.courier.CourierMapper;
import com.migrosone.couriertracking.request.courier.SaveCourierGeoSignalRequest;
import com.migrosone.couriertracking.service.courier.CourierCoreService;
import com.migrosone.couriertracking.service.courier.CourierGeoService;
import com.migrosone.couriertracking.service.courier.CourierGeoSignalCoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CourierGeoServiceImpl implements CourierGeoService {

    private final CourierGeoSignalCoreService courierGeoSignalCoreService;
    private final CourierCoreService courierCoreService;
    private final CourierMapper mapper;

    @Override
    @Transactional
    public Boolean saveCourierGeoSignal(SaveCourierGeoSignalRequest request) {
        CourierGeoSignal savedGeoSignal = courierGeoSignalCoreService.save(request.getCourierGeoSignal());

        Courier foundedCourier = courierCoreService.findById(savedGeoSignal.getCourierId()).orElseThrow(RuntimeException::new);
        BigDecimal travelDistance = BigDecimal.TEN; //TODO: Eklencek seyehat mesafesi hesaplanıyor...
        foundedCourier.setReferenceGeoSignalId(savedGeoSignal.getId());
        foundedCourier.addTotalDistance(travelDistance);
        courierCoreService.save(foundedCourier);

        return Boolean.TRUE;
    }
}
