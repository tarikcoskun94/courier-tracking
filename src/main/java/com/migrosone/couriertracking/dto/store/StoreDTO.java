package com.migrosone.couriertracking.dto.store;

import com.migrosone.couriertracking.dto.EmbeddedAuditDTO;
import com.migrosone.couriertracking.dto.location.PointDTO;
import lombok.Data;

@Data
public class StoreDTO {

    private Long id;
    private String name;
    private PointDTO location;
    private EmbeddedAuditDTO audit;
}
