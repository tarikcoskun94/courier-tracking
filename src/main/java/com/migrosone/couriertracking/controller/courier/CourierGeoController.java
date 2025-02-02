package com.migrosone.couriertracking.controller.courier;

import com.migrosone.couriertracking.request.courier.SaveCourierGeoSignalRequest;
import com.migrosone.couriertracking.service.courier.CourierGeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/couriers")
@RequiredArgsConstructor
public class CourierGeoController {

    private final CourierGeoService service;

    @PostMapping("/geo-signal")
    public ResponseEntity<Boolean> saveCourierGeoSignal(@RequestBody SaveCourierGeoSignalRequest request) {
        return ResponseEntity.ok(service.saveCourierGeoSignal(request));
    }
}
