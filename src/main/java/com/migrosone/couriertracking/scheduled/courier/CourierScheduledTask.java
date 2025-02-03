package com.migrosone.couriertracking.scheduled.courier;

import com.migrosone.couriertracking.entity.courier.CourierGeoSignal;
import com.migrosone.couriertracking.messagequeue.MessageQueue;
import com.migrosone.couriertracking.messagequeue.MessageQueueTopic;
import com.migrosone.couriertracking.service.courier.CourierGeoSignalCoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CourierScheduledTask {

    private static final int ONE_SECOND = 1_000;
    private static final int ONE_MINUTE = ONE_SECOND * 60;
    private static final int COURIER_GEO_SIGNAL_INTERVAL = ONE_MINUTE * 1;

    private final CourierGeoSignalCoreService courierGeoSignalCoreService;

    @Qualifier("fakeMessageQueue")
    private final MessageQueue messageQueue;

    @Async
    @Scheduled(fixedRate = COURIER_GEO_SIGNAL_INTERVAL)
    public void processCourierGeoSignalTopic() {
        List<Object> messages = messageQueue.consumeBatch(MessageQueueTopic.COURIER_GEO_SIGNAL_TOPIC, 50);
        List<CourierGeoSignal> geoSignals = messages.stream()
                .filter(CourierGeoSignal.class::isInstance)
                .map(CourierGeoSignal.class::cast)
                .toList();

        if (!geoSignals.isEmpty()) {
            courierGeoSignalCoreService.saveAll(geoSignals);
            log.info("[processCourierGeoSignalTopic] task has been executed. Count of consumed messages: {}, Time: {}", geoSignals.size(), LocalDateTime.now());
        }
    }
}
