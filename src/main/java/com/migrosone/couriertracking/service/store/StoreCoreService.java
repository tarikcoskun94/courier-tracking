package com.migrosone.couriertracking.service.store;

import com.migrosone.couriertracking.entity.store.Store;
import org.locationtech.jts.geom.Point;

import java.util.List;

public interface StoreCoreService {

    List<Store> findAll();

    List<Store> findByPointInRadius(Point point, double r);
}
