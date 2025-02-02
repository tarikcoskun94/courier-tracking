package com.migrosone.couriertracking.service.courier.impl;

import com.migrosone.couriertracking.entity.courier.CourierGeoSignal;
import com.migrosone.couriertracking.repository.courier.CourierGeoSignalRepository;
import com.migrosone.couriertracking.service.courier.CourierGeoSignalCoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Optional<CourierGeoSignal> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public CourierGeoSignal save(CourierGeoSignal geoSignal) {
        return repository.save(geoSignal);
    }

    @Override
    @Transactional
    public List<CourierGeoSignal> saveAll(List<CourierGeoSignal> geoSignals) {
        return repository.saveAll(geoSignals);
    }
}
