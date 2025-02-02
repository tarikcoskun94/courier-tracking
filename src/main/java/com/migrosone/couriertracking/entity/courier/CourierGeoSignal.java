package com.migrosone.couriertracking.entity.courier;

import com.migrosone.couriertracking.entity.EmbeddedCreationAudit;
import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "COURIER_GEO_SIGNAL")
@EntityListeners(AuditingEntityListener.class)
public class CourierGeoSignal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COURIER_GEO_SIGNAL_SEQ_GEN")
    @SequenceGenerator(name = "COURIER_GEO_SIGNAL_SEQ_GEN", sequenceName = "COURIER_GEO_SIGNAL_SEQ", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "COURIER_ID", updatable = false, nullable = false)
    private Long courierId;

    @Column(name = "LOCATION", updatable = false, nullable = false)
    private Point location;

    @Column(name = "COURIER_TIME", updatable = false, nullable = false)
    private LocalDateTime courierTime;

    @Version
    @Column(name = "VERSION", updatable = false, nullable = false)
    private Long version = 1L;

    @Embedded
    private EmbeddedCreationAudit creationAudit = new EmbeddedCreationAudit();
}
