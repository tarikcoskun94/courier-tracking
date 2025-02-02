package com.migrosone.couriertracking.service.courier.impl;

import com.migrosone.couriertracking.entity.courier.Courier;
import com.migrosone.couriertracking.repository.courier.CourierRepository;
import com.migrosone.couriertracking.service.courier.CourierCoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourierCoreServiceImpl implements CourierCoreService {

    private final CourierRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Courier> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Courier> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Courier save(Courier courier) {
        return repository.save(courier);
    }

    @Override
    @Transactional
    public List<Courier> saveAll(List<Courier> couriers) {
        return repository.saveAll(couriers);
    }
}
