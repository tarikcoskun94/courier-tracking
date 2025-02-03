package com.migrosone.couriertracking.service.courier.impl;

import com.migrosone.couriertracking.entity.courier.CourierDrive;
import com.migrosone.couriertracking.repository.courier.CourierDriveRepository;
import com.migrosone.couriertracking.service.courier.CourierDriveCoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourierDriveCoreServiceImpl implements CourierDriveCoreService {

    private final CourierDriveRepository repository;

    @Override
    @Transactional(readOnly = true)
    public boolean existActiveDriveByCourierId(Long courierId) {
        return repository.existActiveDriveByCourierId(courierId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CourierDrive> findActiveDriveByCourierId(Long courierId) {
        return repository.findActiveDriveByCourierId(courierId);
    }

    @Override
    @Transactional
    public CourierDrive save(CourierDrive courierDrive) {
        return repository.save(courierDrive);
    }
}
