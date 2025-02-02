package com.migrosone.couriertracking.service.courier;

import com.migrosone.couriertracking.entity.courier.Courier;

import java.util.List;
import java.util.Optional;

public interface CourierCoreService {

    List<Courier> findAll();

    Optional<Courier> findById(Long id);

    Courier save(Courier courier);

    List<Courier> saveAll(List<Courier> couriers);
}
