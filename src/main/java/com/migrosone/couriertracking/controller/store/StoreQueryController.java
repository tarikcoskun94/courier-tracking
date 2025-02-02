package com.migrosone.couriertracking.controller.store;

import com.migrosone.couriertracking.response.store.GetAllStoresResponse;
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
    public ResponseEntity<GetAllStoresResponse> getAllStores() {
        return ResponseEntity.ok(
                GetAllStoresResponse.of(service.getAllStores())
        );
    }
}
