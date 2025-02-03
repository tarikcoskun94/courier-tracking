package com.migrosone.couriertracking.service.courier;

import com.migrosone.couriertracking.request.courier.SaveCourierGeoSignalRequest;

public interface CourierGeoService {

    void receiveCourierGeoSignal(SaveCourierGeoSignalRequest request);

    void startDrive(SaveCourierGeoSignalRequest request);

    void finishDrive(SaveCourierGeoSignalRequest request);
}
