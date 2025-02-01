package com.migrosone.couriertracking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Embeddable
public class EmbeddedAudit implements Serializable {

    @CreatedBy
    @Column(name = "CREATOR_USER", updatable = false)
    private String creatorUser;

    @CreatedDate
    @Column(name = "CREATION_DATE", updatable = false)
    private LocalDateTime creationDate;

    @LastModifiedBy
    @Column(name = "UPDATER_USER")
    private String updaterUser;

    @LastModifiedDate
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;
}
