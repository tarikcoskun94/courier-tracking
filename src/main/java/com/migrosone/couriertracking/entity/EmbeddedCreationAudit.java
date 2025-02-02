package com.migrosone.couriertracking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Embeddable
public class EmbeddedCreationAudit implements Serializable {

    @CreatedBy
    @Column(name = "CREATOR_USER", updatable = false, nullable = false, length = 100)
    private String creatorUser;

    @CreatedDate
    @Column(name = "CREATION_DATE", updatable = false, nullable = false)
    private LocalDateTime creationDate;
}
