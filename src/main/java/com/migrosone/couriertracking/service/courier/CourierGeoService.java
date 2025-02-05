package com.migrosone.couriertracking.service.courier;

import com.migrosone.couriertracking.request.courier.SaveCourierGeoSignalRequest;

public interface CourierGeoService {

    void startDrive(SaveCourierGeoSignalRequest request);

    void receiveCourierGeoSignal(SaveCourierGeoSignalRequest request);

    void finishDrive(SaveCourierGeoSignalRequest request);
}
