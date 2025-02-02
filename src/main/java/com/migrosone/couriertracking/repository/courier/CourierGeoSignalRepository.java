package com.migrosone.couriertracking.repository.courier;

import com.migrosone.couriertracking.entity.courier.CourierGeoSignal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierGeoSignalRepository extends JpaRepository<CourierGeoSignal, Long> {
}
