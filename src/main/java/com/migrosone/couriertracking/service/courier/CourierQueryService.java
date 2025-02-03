package com.migrosone.couriertracking.service.courier;

import com.migrosone.couriertracking.dto.courier.CourierDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CourierQueryService {
    List<CourierDTO> getAllCouriers();

    BigDecimal getTotalDistanceById(Long id);
}
