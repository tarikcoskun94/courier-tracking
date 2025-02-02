package com.migrosone.couriertracking.entity.store;

import com.migrosone.couriertracking.entity.EmbeddedCreationAudit;
import com.migrosone.couriertracking.entity.EmbeddedUpdateAudit;
import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Data
@Entity
@Table(name = "STORE")
@EntityListeners(AuditingEntityListener.class)
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STORE_SEQ_GEN")
    @SequenceGenerator(name = "STORE_SEQ_GEN", sequenceName = "STORE_SEQ", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "LOCATION", nullable = false)
    private Point location;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version = 1L;

    @Embedded
    private EmbeddedCreationAudit creationAudit;

    @Embedded
    private EmbeddedUpdateAudit updateAudit;
}
