package com.migrosone.couriertracking.dto.courier;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CourierDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Long referenceLocationId;
    private BigDecimal totalDistance;
    private String creatorUser;
    private LocalDateTime creationDate;
    private String updaterUser;
    private LocalDateTime updateDate;
}
