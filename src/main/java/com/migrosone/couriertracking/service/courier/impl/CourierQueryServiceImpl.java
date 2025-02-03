package com.migrosone.couriertracking.service.courier.impl;

import com.migrosone.couriertracking.dto.courier.CourierDTO;
import com.migrosone.couriertracking.mapper.courier.CourierMapper;
import com.migrosone.couriertracking.service.courier.CourierCoreService;
import com.migrosone.couriertracking.service.courier.CourierQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourierQueryServiceImpl implements CourierQueryService {

    private final CourierCoreService coreService;
    private final CourierMapper mapper;

    @Override
    public List<CourierDTO> getAllCouriers() {
        return coreService.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public BigDecimal getTotalDistanceById(Long id) {
        return coreService.findTotalDistanceById(id);
    }
}
