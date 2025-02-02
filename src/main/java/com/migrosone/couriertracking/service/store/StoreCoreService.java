package com.migrosone.couriertracking.service.store;

import com.migrosone.couriertracking.entity.store.Store;

import java.util.List;

public interface StoreCoreService {
    List<Store> findAll();
}
