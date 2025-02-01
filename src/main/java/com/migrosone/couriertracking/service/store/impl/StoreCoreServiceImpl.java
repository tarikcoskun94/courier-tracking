package com.migrosone.couriertracking.service.store.impl;

import com.migrosone.couriertracking.entity.store.Store;
import com.migrosone.couriertracking.repository.store.StoreRepository;
import com.migrosone.couriertracking.service.store.StoreCoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreCoreServiceImpl implements StoreCoreService {

    private final StoreRepository repository;

    @Override
    public List<Store> getAllStores() {
        return repository.findAll();
    }
}
