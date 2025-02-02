package com.migrosone.couriertracking.controller.courier;

import com.migrosone.couriertracking.response.courier.GetAllCouriersResponse;
import com.migrosone.couriertracking.service.courier.CourierQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/couriers")
@RequiredArgsConstructor
public class CourierQueryController {

    private final CourierQueryService service;

    @GetMapping()
    public ResponseEntity<GetAllCouriersResponse> getAllCouriers() {
        return ResponseEntity.ok(
                GetAllCouriersResponse.of(service.getAllCouriers())
        );
    }
}
