package com.migrosone.couriertracking.messagequeue;

import java.util.List;

public interface MessageQueue {

    void publish(String topic, Object message);

    Object consume(String topic);

    List<Object> consumeBatch(String topic, int batchSize);

    List<Object> consumeToFinishDrive(Long courierId);
}
