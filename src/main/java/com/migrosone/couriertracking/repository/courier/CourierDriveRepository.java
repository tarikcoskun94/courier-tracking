package com.migrosone.couriertracking.repository.courier;

import com.migrosone.couriertracking.entity.courier.CourierDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourierDriveRepository extends JpaRepository<CourierDrive, Long> {

    @Query(value = CourierDriveQuery.EXIST_ACTIVE_DRIVE_BY_COURIER_ID, nativeQuery = true)
    boolean existActiveDriveByCourierId(Long courierId);

    @Query(value = CourierDriveQuery.FIND_ACTIVE_DRIVE_BY_COURIER_ID, nativeQuery = true)
    Optional<CourierDrive> findActiveDriveByCourierId(Long courierId);
}
