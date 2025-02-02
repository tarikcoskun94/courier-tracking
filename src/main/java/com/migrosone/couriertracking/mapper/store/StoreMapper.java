package com.migrosone.couriertracking.mapper.store;

import com.migrosone.couriertracking.dto.store.StoreDTO;
import com.migrosone.couriertracking.entity.store.Store;
import com.migrosone.couriertracking.mapper.location.PointMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreMapper {

    private final PointMapper pointMapper;

    public Store toEntity(StoreDTO dto) {
        if (dto == null) {
            return null;
        }

        Store store = new Store();
        store.setId(dto.getId());
        store.setName(dto.getName());
        store.setLocation(pointMapper.toPoint(dto.getLocation()));

        return store;
    }

    public StoreDTO toDto(Store entity) {
        if (entity == null) {
            return null;
        }

        StoreDTO dto = new StoreDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLocation(pointMapper.toDto(entity.getLocation()));

        if (entity.getCreationAudit() != null) {
            dto.setCreatorUser(entity.getCreationAudit().getCreatorUser());
            dto.setCreationDate(entity.getCreationAudit().getCreationDate());
        }

        if (entity.getUpdateAudit() != null) {
            dto.setUpdaterUser(entity.getUpdateAudit().getUpdaterUser());
            dto.setUpdateDate(entity.getUpdateAudit().getUpdateDate());
        }

        return dto;
    }
}
