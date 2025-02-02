package com.migrosone.couriertracking.repository.courier;

import com.migrosone.couriertracking.entity.courier.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
}
