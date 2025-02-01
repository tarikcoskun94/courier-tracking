package com.migrosone.couriertracking.entity.store;

import com.migrosone.couriertracking.entity.EmbeddedAudit;
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
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LOCATION")
    private Point location;

    @Version
    @Column(name = "VERSION")
    private Long version;

    @Embedded
    private EmbeddedAudit audit;
}
