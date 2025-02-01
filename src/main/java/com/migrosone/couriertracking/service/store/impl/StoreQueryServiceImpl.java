package com.migrosone.couriertracking.service.store.impl;

import com.migrosone.couriertracking.dto.store.StoreDTO;
import com.migrosone.couriertracking.mapper.store.StoreMapper;
import com.migrosone.couriertracking.service.store.StoreQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreCoreServiceImpl coreService;
    private final StoreMapper mapper;

    @Override
    public List<StoreDTO> getAllStores() {
        return coreService.getAllStores().stream()
                .map(mapper::toDto)
                .toList();
    }
}
