package com.migrosone.couriertracking.dto.store;

import com.migrosone.couriertracking.dto.location.PointDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoreDTO {

    private Long id;
    private String name;
    private PointDTO location;
    private String creatorUser;
    private LocalDateTime creationDate;
    private String updaterUser;
    private LocalDateTime updateDate;
}
