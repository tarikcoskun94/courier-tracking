package com.migrosone.couriertracking.service.courier.impl;

import com.migrosone.couriertracking.dto.location.PointDTO;
import com.migrosone.couriertracking.entity.courier.CourierDrive;
import com.migrosone.couriertracking.entity.courier.CourierGeoSignal;
import com.migrosone.couriertracking.messagequeue.MessageQueue;
import com.migrosone.couriertracking.request.courier.SaveCourierGeoSignalRequest;
import com.migrosone.couriertracking.service.courier.CourierDriveCoreService;
import com.migrosone.couriertracking.service.courier.CourierGeoSignalCoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourierGeoServiceImplTest {

    @InjectMocks
    public CourierGeoServiceImpl service;

    @Mock
    public MessageQueue messageQueue;
    @Mock
    public ApplicationEventPublisher eventPublisher;
    @Mock
    public CourierDriveCoreService courierDriveCoreService;
    @Mock
    public CourierGeoSignalCoreService courierGeoSignalCoreService;

    @Test
    void givenExistActiveDrive_whenStartDrive_thenThrowException() {
        final SaveCourierGeoSignalRequest request = new SaveCourierGeoSignalRequest();
        request.setCourierId(100L);

        when(courierDriveCoreService.findActiveDriveByCourierId(100L)).thenReturn(Optional.of(new CourierDrive()));

        assertThrows(IllegalStateException.class, () -> service.startDrive(request));

        verify(courierDriveCoreService, never()).save(any(CourierDrive.class));
    }

    @Test
    void givenStartableDrive_whenStartDrive_thenStartDrive() {
        final PointDTO location = new PointDTO();
        location.setLatitude(40.98992133009554);
        location.setLongitude(29.12197657770705);

        final SaveCourierGeoSignalRequest request = new SaveCourierGeoSignalRequest();
        request.setCourierId(200L);
        request.setLocation(location);
        request.setTime(LocalDateTime.parse("1900-01-01T00:00:00.000000"));

        final CourierGeoSignal savedGeoSignal = new CourierGeoSignal();
        savedGeoSignal.setId(500L);

        when(courierDriveCoreService.findActiveDriveByCourierId(200L)).thenReturn(Optional.empty());
        when(courierGeoSignalCoreService.save(any(CourierGeoSignal.class))).thenReturn(savedGeoSignal);
        when(courierDriveCoreService.save(any(CourierDrive.class))).thenReturn(new CourierDrive());

        service.startDrive(request);

        final ArgumentCaptor<CourierGeoSignal> geoSignalCaptor = ArgumentCaptor.forClass(CourierGeoSignal.class);
        verify(courierGeoSignalCoreService).save(geoSignalCaptor.capture());
        final CourierGeoSignal capturedGeoSignal = geoSignalCaptor.getValue();

        assertNull(capturedGeoSignal.getId());
        assertEquals(200L, capturedGeoSignal.getCourierId());
        assertEquals(40.98992133009554, capturedGeoSignal.getLocation().getY());
        assertEquals(29.12197657770705, capturedGeoSignal.getLocation().getX());
        assertEquals(LocalDateTime.parse("1900-01-01T00:00:00.000000"), capturedGeoSignal.getCourierTime());
        assertEquals(1, capturedGeoSignal.getVersion());
        assertNull(capturedGeoSignal.getCreationAudit().getCreatorUser());
        assertNull(capturedGeoSignal.getCreationAudit().getCreationDate());

        final ArgumentCaptor<CourierDrive> driveCaptor = ArgumentCaptor.forClass(CourierDrive.class);
        verify(courierDriveCoreService).save(driveCaptor.capture());
        final CourierDrive capturedDrive = driveCaptor.getValue();

        assertNull(capturedDrive.getId());
        assertEquals(500L, capturedDrive.getStarterGeoSignalId());
        assertEquals(200L, capturedDrive.getCourierId());
        assertEquals(1, capturedDrive.getVersion());
        assertNull(capturedDrive.getFinisherGeoSignalId());
        assertNull(capturedDrive.getCreationAudit().getCreatorUser());
        assertNull(capturedDrive.getCreationAudit().getCreationDate());
        assertNull(capturedDrive.getUpdateAudit().getUpdaterUser());
        assertNull(capturedDrive.getUpdateAudit().getUpdateDate());
    }
}