package com.migrosone.couriertracking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Embeddable
public class EmbeddedUpdateAudit implements Serializable {

    @LastModifiedBy
    @Column(name = "UPDATER_USER", length = 100)
    private String updaterUser;

    @LastModifiedDate
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;
}
