package com.migrosone.couriertracking.controller.courier;

import com.migrosone.couriertracking.request.courier.SaveCourierGeoSignalRequest;
import com.migrosone.couriertracking.service.courier.CourierGeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/couriers")
@RequiredArgsConstructor
public class CourierGeoController {

    private final CourierGeoService service;

    @PostMapping("/start-drive")
    public void startDrive(@RequestBody SaveCourierGeoSignalRequest request) {
        service.startDrive(request);
    }

    @PostMapping("/geo-signal")
    public void receiveCourierGeoSignal(@RequestBody SaveCourierGeoSignalRequest request) {
        service.receiveCourierGeoSignal(request);
    }

    @PostMapping("/finish-drive")
    public void finishDrive(@RequestBody SaveCourierGeoSignalRequest request) {
        service.finishDrive(request);
    }
}
