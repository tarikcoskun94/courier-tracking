package com.migrosone.couriertracking.service.courier;

import com.migrosone.couriertracking.entity.courier.CourierDrive;

import java.util.Optional;

public interface CourierDriveCoreService {

    boolean existActiveDriveByCourierId(Long courierId);

    Optional<CourierDrive> findActiveDriveByCourierId(Long courierId);

    CourierDrive save(CourierDrive courierDrive);
}
