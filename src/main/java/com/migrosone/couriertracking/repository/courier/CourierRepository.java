package com.migrosone.couriertracking.repository.courier;

import com.migrosone.couriertracking.entity.courier.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {

    @Query(value = CourierQuery.FIND_TOTAL_DISTANCE_BY_ID, nativeQuery = true)
    BigDecimal findTotalDistanceById(Long id);
}
