package com.migrosone.couriertracking.service.courier;

import com.migrosone.couriertracking.request.courier.SaveCourierGeoSignalRequest;

public interface CourierGeoService {
    Boolean saveCourierGeoSignal(SaveCourierGeoSignalRequest request);
}
