package com.migrosone.couriertracking.repository.store;

import com.migrosone.couriertracking.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query(value = StoreQuery.FIND_BY_POINT_IN_RADIUS, nativeQuery = true)
    List<Store> findByPointInRadius(double lon, double lat, double r);
}
