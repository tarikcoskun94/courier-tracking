package com.migrosone.couriertracking.service.courier;

import com.migrosone.couriertracking.entity.courier.Courier;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CourierCoreService {

    List<Courier> findAll();

    List<Courier> findAllById(Iterable<Long> ids);

    Optional<Courier> findById(Long id);

    BigDecimal findTotalDistanceById(Long id);

    Courier save(Courier courier);

    List<Courier> saveAll(Iterable<Courier> couriers);
}
