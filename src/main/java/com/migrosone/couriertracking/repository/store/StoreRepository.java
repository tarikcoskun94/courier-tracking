package com.migrosone.couriertracking.repository.store;

import com.migrosone.couriertracking.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}
