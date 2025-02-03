package com.migrosone.couriertracking.service.courier.impl;

import com.migrosone.couriertracking.entity.courier.CourierDrive;
import com.migrosone.couriertracking.entity.courier.CourierGeoSignal;
import com.migrosone.couriertracking.repository.courier.CourierGeoSignalRepository;
import com.migrosone.couriertracking.service.courier.CourierGeoSignalCoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourierGeoSignalCoreServiceImpl implements CourierGeoSignalCoreService {

    private final CourierGeoSignalRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<CourierGeoSignal> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourierGeoSignal> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CourierGeoSignal> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal calculateTravel(CourierDrive finishedDrive) {
        if (finishedDrive == null || finishedDrive.isNotFinished()) {
            throw new IllegalStateException("Cannot calculate travel for not finished drive");
        }

        return repository.calculateDistanceBetweenTwoSignalByCourierId(
                finishedDrive.getCourierId(),
                finishedDrive.getStarterGeoSignalId(),
                finishedDrive.getFinisherGeoSignalId()
        );
    }

    @Override
    @Transactional
    public CourierGeoSignal save(CourierGeoSignal geoSignal) {
        return repository.save(geoSignal);
    }

    @Override
    @Transactional
    public List<CourierGeoSignal> saveAll(Iterable<CourierGeoSignal> geoSignals) {
        return repository.saveAll(geoSignals);
    }
}
