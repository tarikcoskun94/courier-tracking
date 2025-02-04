package com.migrosone.couriertracking.service.courier.impl;

import com.migrosone.couriertracking.entity.courier.CourierDrive;
import com.migrosone.couriertracking.entity.courier.CourierGeoSignal;
import com.migrosone.couriertracking.event.courier.CourierDriveFinishEvent;
import com.migrosone.couriertracking.event.courier.CourierGeoSignalReceivedEvent;
import com.migrosone.couriertracking.messagequeue.MessageQueue;
import com.migrosone.couriertracking.messagequeue.MessageQueueTopic;
import com.migrosone.couriertracking.request.courier.SaveCourierGeoSignalRequest;
import com.migrosone.couriertracking.service.courier.CourierDriveCoreService;
import com.migrosone.couriertracking.service.courier.CourierGeoService;
import com.migrosone.couriertracking.service.courier.CourierGeoSignalCoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourierGeoServiceImpl implements CourierGeoService {

    @Qualifier("fakeMessageQueue")
    private final MessageQueue messageQueue;

    private final ApplicationEventPublisher eventPublisher;

    private final CourierDriveCoreService courierDriveCoreService;
    private final CourierGeoSignalCoreService courierGeoSignalCoreService;

    @Override
    public void receiveCourierGeoSignal(SaveCourierGeoSignalRequest request) {
        if (!courierDriveCoreService.existActiveDriveByCourierId(request.getCourierId())) {
            throw new IllegalStateException("No active drive found for the courier");
        }

        messageQueue.publish(MessageQueueTopic.COURIER_GEO_SIGNAL_TOPIC, request.getCourierGeoSignal());
        eventPublisher.publishEvent(CourierGeoSignalReceivedEvent.of(this, request.getCourierGeoSignal()));
    }

    @Override
    @Transactional
    public void startDrive(SaveCourierGeoSignalRequest request) {
        Optional<CourierDrive> activeDriveOpt = courierDriveCoreService.findActiveDriveByCourierId(request.getCourierId());

        if (activeDriveOpt.isPresent()) {
            throw new IllegalStateException("Already exist drive found for the courier");
        }

        CourierGeoSignal starterSignal = courierGeoSignalCoreService.save(request.getCourierGeoSignal());
        CourierDrive startingDrive = CourierDrive.start(request.getCourierId(), starterSignal.getId());
        courierDriveCoreService.save(startingDrive);
    }

    @Override
    @Transactional
    public void finishDrive(SaveCourierGeoSignalRequest request) {
        CourierDrive activeDrive = courierDriveCoreService.findActiveDriveByCourierId(request.getCourierId())
                .orElseThrow(() -> new IllegalStateException("No active drive found for the courier"));

        List<Object> messages = messageQueue.consumeToFinishDrive(activeDrive.getCourierId());
        List<CourierGeoSignal> geoSignalsInQueue = messages.stream()
                .filter(CourierGeoSignal.class::isInstance)
                .map(CourierGeoSignal.class::cast)
                .toList();
        courierGeoSignalCoreService.saveAll(geoSignalsInQueue);

        CourierGeoSignal finisherSignal = courierGeoSignalCoreService.save(request.getCourierGeoSignal());
        activeDrive.finish(finisherSignal.getId());
        CourierDrive finishedDrive = courierDriveCoreService.save(activeDrive);

        eventPublisher.publishEvent(CourierDriveFinishEvent.of(this, finishedDrive));
    }
}
