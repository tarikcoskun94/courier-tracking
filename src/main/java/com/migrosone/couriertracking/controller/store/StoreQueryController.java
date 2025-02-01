package com.migrosone.couriertracking.controller.store;

import com.migrosone.couriertracking.response.store.AllStoresResponse;
import com.migrosone.couriertracking.service.store.StoreQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreQueryController {

    private final StoreQueryService service;

    @GetMapping()
    public ResponseEntity<AllStoresResponse> getAllStores() {
        return ResponseEntity.ok(
                AllStoresResponse.of(service.getAllStores())
        );
    }
}
