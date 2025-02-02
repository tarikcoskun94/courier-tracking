package com.migrosone.couriertracking.mapper.courier;

import com.migrosone.couriertracking.dto.courier.CourierDTO;
import com.migrosone.couriertracking.entity.courier.Courier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourierMapper {

    public Courier toEntity(CourierDTO dto) {
        if (dto == null) {
            return null;
        }

        Courier courier = new Courier();
        courier.setId(dto.getId());
        courier.setFirstName(dto.getFirstName());
        courier.setLastName(dto.getLastName());
        courier.setReferenceGeoSignalId(dto.getReferenceLocationId());
        courier.setTotalDistance(dto.getTotalDistance());

        return courier;
    }

    public CourierDTO toDto(Courier entity) {
        if (entity == null) {
            return null;
        }

        CourierDTO courierDTO = new CourierDTO();
        courierDTO.setId(entity.getId());
        courierDTO.setFirstName(entity.getFirstName());
        courierDTO.setLastName(entity.getLastName());
        courierDTO.setReferenceLocationId(entity.getReferenceGeoSignalId());
        courierDTO.setTotalDistance(entity.getTotalDistance());

        if (entity.getCreationAudit() != null) {
            courierDTO.setCreatorUser(entity.getCreationAudit().getCreatorUser());
            courierDTO.setCreationDate(entity.getCreationAudit().getCreationDate());
        }

        if (entity.getUpdateAudit() != null) {
            courierDTO.setUpdaterUser(entity.getUpdateAudit().getUpdaterUser());
            courierDTO.setUpdateDate(entity.getUpdateAudit().getUpdateDate());
        }

        return courierDTO;
    }
}
