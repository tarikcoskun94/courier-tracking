package com.migrosone.couriertracking.repository.courier;

import com.migrosone.couriertracking.entity.courier.CourierGeoSignal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CourierGeoSignalRepository extends JpaRepository<CourierGeoSignal, Long> {

    @Query(value = CourierGeoSignalQuery.CALCULATE_DISTANCE_BETWEEN_TWO_SIGNAL_BY_COURIER_ID, nativeQuery = true)
    BigDecimal calculateDistanceBetweenTwoSignalByCourierId(Long courierId, Long startSignalId, Long endSignalId);
}
