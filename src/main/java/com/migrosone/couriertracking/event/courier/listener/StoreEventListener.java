package com.migrosone.couriertracking.event.courier.listener;

import com.migrosone.couriertracking.cache.Cache;
import com.migrosone.couriertracking.cache.CachePrefix;
import com.migrosone.couriertracking.constant.DateTimeFormat;
import com.migrosone.couriertracking.entity.store.Store;
import com.migrosone.couriertracking.event.courier.CourierGeoSignalReceivedEvent;
import com.migrosone.couriertracking.service.file.FileService;
import com.migrosone.couriertracking.service.store.StoreCoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.Duration;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class StoreEventListener {

    @Qualifier("redisStringCache")
    private final Cache<String, String> cache;

    private final StoreCoreService storeCoreService;
    private final FileService fileService;

    @EventListener
    public void handleCourierGeoSignalReceivedEvent(CourierGeoSignalReceivedEvent event) {
        List<Store> courierInRangeStores = storeCoreService.findByPointInRadius(event.getLocation(), 100);

        List<Store> courierInRangeStoresByDuration = courierInRangeStores.stream()
                .filter(store -> {
                    String cacheKey = getCacheKeyForCourierInRangeOfStore(event.getCourierId(), store.getId());
                    return cache.get(cacheKey) == null;
                })
                .toList();

        for (Store store : courierInRangeStoresByDuration) {
            String cacheKey = getCacheKeyForCourierInRangeOfStore(event.getCourierId(), store.getId());
            cache.put(cacheKey, "courierInRangeOfStore", Duration.ofMinutes(1));

            String logMessage = "The courier was entered range of the store -> [courierId: {0}, storeId: {1}, time: {2}]";

            try {
                fileService.save(
                        "logs/courierInRangeOfStore.log",
                        logMessage,
                        event.getCourierId(),
                        store.getId(),
                        event.getCourierTime().format(DateTimeFormat.DEFAULT)
                );
            } catch (IOException ex) {
                log.warn(MessageFormat.format(logMessage, event.getCourierId(), store.getId()), ex);
            }
        }
    }

    private String getCacheKeyForCourierInRangeOfStore(Long courierId, Long storeId) {
        return CachePrefix.COURIER_IN_RANGE_OF_STORE + courierId.toString() + storeId.toString();
    }
}
