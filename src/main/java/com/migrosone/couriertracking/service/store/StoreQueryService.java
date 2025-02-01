package com.migrosone.couriertracking.service.store;

import com.migrosone.couriertracking.dto.store.StoreDTO;

import java.util.List;

public interface StoreQueryService {
    List<StoreDTO> getAllStores();
}
