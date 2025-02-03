package com.migrosone.couriertracking.messagequeue;

import com.migrosone.couriertracking.entity.courier.CourierGeoSignal;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Component("fakeMessageQueue")
public class FakeMessageQueue implements MessageQueue {

    private final ConcurrentHashMap<String, BlockingQueue<Object>> topics = new ConcurrentHashMap<>();

    @Override
    public void publish(String topic, Object message) {
        topics.computeIfAbsent(topic, k -> new LinkedBlockingQueue<>()).offer(message);
    }

    @Override
    public Object consume(String topic) {
        BlockingQueue<Object> queue = topics.get(topic);
        return (queue != null) ? queue.poll() : null;
    }

    @Override
    public List<Object> consumeBatch(String topic, int batchSize) {
        BlockingQueue<Object> queue = topics.get(topic);
        if (queue == null) return Collections.emptyList();

        List<Object> batch = new ArrayList<>();
        queue.drainTo(batch, batchSize);
        return batch;
    }

    @Override
    public List<Object> consumeToFinishDrive(Long courierId) {
        BlockingQueue<Object> queue = topics.get(MessageQueueTopic.COURIER_GEO_SIGNAL_TOPIC);
        if (queue == null) return Collections.emptyList();

        List<Object> relatedMessages = new ArrayList<>();
        queue.forEach(message -> {
            if (message instanceof CourierGeoSignal geoSignal &&
                    geoSignal.getCourierId().equals(courierId)) {
                relatedMessages.add(geoSignal);
            }
        });

        relatedMessages.forEach(queue::remove);
        return relatedMessages;
    }
}
