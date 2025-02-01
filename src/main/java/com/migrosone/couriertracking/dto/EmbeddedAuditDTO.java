package com.migrosone.couriertracking.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class EmbeddedAuditDTO implements Serializable {

    private String creatorUser;
    private LocalDateTime creationDate;
    private String updaterUser;
    private LocalDateTime updateDate;
}
