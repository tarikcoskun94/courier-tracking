package com.migrosone.couriertracking.mapper.store;

import com.migrosone.couriertracking.dto.store.StoreDTO;
import com.migrosone.couriertracking.entity.store.Store;
import com.migrosone.couriertracking.mapper.EmbeddedAuditMapper;
import com.migrosone.couriertracking.mapper.location.PointMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreMapper {

    private final PointMapper pointMapper;
    private final EmbeddedAuditMapper embeddedAuditMapper;

    public Store toEntity(StoreDTO dto) {
        if (dto == null) {
            return null;
        }

        Store store = new Store();
        store.setId(dto.getId());
        store.setName(dto.getName());
        store.setLocation(pointMapper.toPoint(dto.getLocation()));
        store.setAudit(embeddedAuditMapper.toAudit(dto.getAudit()));

        return store;
    }

    public StoreDTO toDto(Store store) {
        if (store == null) {
            return null;
        }

        StoreDTO dto = new StoreDTO();
        dto.setId(store.getId());
        dto.setName(store.getName());
        dto.setLocation(pointMapper.toDto(store.getLocation()));
        dto.setAudit(embeddedAuditMapper.toDto(store.getAudit()));

        return dto;
    }
}
