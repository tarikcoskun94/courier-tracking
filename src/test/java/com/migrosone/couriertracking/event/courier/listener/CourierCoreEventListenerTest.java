package com.migrosone.couriertracking.event.courier.listener;

import com.migrosone.couriertracking.entity.EmbeddedCreationAudit;
import com.migrosone.couriertracking.entity.EmbeddedUpdateAudit;
import com.migrosone.couriertracking.entity.courier.Courier;
import com.migrosone.couriertracking.entity.courier.CourierDrive;
import com.migrosone.couriertracking.event.courier.CourierDriveFinishEvent;
import com.migrosone.couriertracking.service.courier.CourierCoreService;
import com.migrosone.couriertracking.service.courier.CourierGeoSignalCoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourierCoreEventListenerTest {

    @InjectMocks
    public CourierCoreEventListener listener;

    @Mock
    public CourierCoreService courierCoreService;
    @Mock
    public CourierGeoSignalCoreService courierGeoSignalCoreService;

    @Test
    void caughtEvent_whenHandleCourierDriveFinishEvent_thenFinishWithoutError() {
        final EmbeddedCreationAudit creationAudit = new EmbeddedCreationAudit();
        creationAudit.setCreationDate(LocalDateTime.of(2025, 1, 1, 0, 0, 0, 0));
        creationAudit.setCreatorUser("SYSTEM_CR");

        final EmbeddedUpdateAudit updateAudit = new EmbeddedUpdateAudit();
        updateAudit.setUpdateDate(LocalDateTime.of(2026, 1, 1, 0, 0, 0, 0));
        updateAudit.setUpdaterUser("SYSTEM_UP");

        final CourierDrive finishedDriveOnEvent = new CourierDrive();
        finishedDriveOnEvent.setId(100L);
        finishedDriveOnEvent.setCourierId(200L);
        finishedDriveOnEvent.setStarterGeoSignalId(301L);
        finishedDriveOnEvent.setFinisherGeoSignalId(300L);
        finishedDriveOnEvent.setVersion(4L);
        finishedDriveOnEvent.setCreationAudit(creationAudit);
        finishedDriveOnEvent.setUpdateAudit(updateAudit);

        final Courier foundCourier = new Courier();
        foundCourier.setId(100L);
        foundCourier.setFirstName("Ali");
        foundCourier.setLastName("MELEK");
        foundCourier.setTotalDistance(BigDecimal.ONE);
        foundCourier.setVersion(1L);
        foundCourier.setCreationAudit(creationAudit);
        foundCourier.setUpdateAudit(updateAudit);

        when(courierCoreService.findById(200L)).thenReturn(Optional.of(foundCourier));
        when(courierGeoSignalCoreService.calculateTravel(finishedDriveOnEvent)).thenReturn(BigDecimal.TEN);
        when(courierCoreService.save(foundCourier)).thenReturn(any(Courier.class));

        listener.handleCourierDriveFinishEvent(CourierDriveFinishEvent.of(this, finishedDriveOnEvent));

        captureFinishedDriveOnEventAndAssert();
        captureFoundCourierAndAssert();

    }

    private void captureFinishedDriveOnEventAndAssert() {
        final ArgumentCaptor<CourierDrive> finishedDriveOnEventCaptor = ArgumentCaptor.forClass(CourierDrive.class);
        verify(courierGeoSignalCoreService).calculateTravel(finishedDriveOnEventCaptor.capture());
        final CourierDrive capturedFinishedDriveOnEvent = finishedDriveOnEventCaptor.getValue();

        assertEquals(100L, capturedFinishedDriveOnEvent.getId());
        assertEquals(200L, capturedFinishedDriveOnEvent.getCourierId());
        assertEquals(301L, capturedFinishedDriveOnEvent.getStarterGeoSignalId());
        assertEquals(300L, capturedFinishedDriveOnEvent.getFinisherGeoSignalId());
        assertEquals(4L, capturedFinishedDriveOnEvent.getVersion());
        assertEquals(LocalDateTime.of(2025, 1, 1, 0, 0, 0, 0), capturedFinishedDriveOnEvent.getCreationAudit().getCreationDate());
        assertEquals("SYSTEM_CR", capturedFinishedDriveOnEvent.getCreationAudit().getCreatorUser());
        assertEquals(LocalDateTime.of(2026, 1, 1, 0, 0, 0, 0), capturedFinishedDriveOnEvent.getUpdateAudit().getUpdateDate());
        assertEquals("SYSTEM_UP", capturedFinishedDriveOnEvent.getUpdateAudit().getUpdaterUser());
    }

    private void captureFoundCourierAndAssert() {
        final ArgumentCaptor<Courier> foundCourierCaptor = ArgumentCaptor.forClass(Courier.class);
        verify(courierCoreService).save(foundCourierCaptor.capture());
        final Courier capturedCourier = foundCourierCaptor.getValue();

        assertEquals(100L, capturedCourier.getId());
        assertEquals("Ali", capturedCourier.getFirstName());
        assertEquals("MELEK", capturedCourier.getLastName());
        assertEquals(BigDecimal.valueOf(11), capturedCourier.getTotalDistance());
        assertEquals(1L, capturedCourier.getVersion());
        assertEquals(LocalDateTime.of(2025, 1, 1, 0, 0, 0, 0), capturedCourier.getCreationAudit().getCreationDate());
        assertEquals("SYSTEM_CR", capturedCourier.getCreationAudit().getCreatorUser());
        assertEquals(LocalDateTime.of(2026, 1, 1, 0, 0, 0, 0), capturedCourier.getUpdateAudit().getUpdateDate());
        assertEquals("SYSTEM_UP", capturedCourier.getUpdateAudit().getUpdaterUser());
    }
}