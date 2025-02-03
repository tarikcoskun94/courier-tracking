package com.migrosone.couriertracking.entity.courier;

import com.migrosone.couriertracking.entity.EmbeddedCreationAudit;
import com.migrosone.couriertracking.entity.EmbeddedUpdateAudit;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "COURIER")
@EntityListeners(AuditingEntityListener.class)
public class Courier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COURIER_SEQ_GEN")
    @SequenceGenerator(name = "COURIER_SEQ_GEN", sequenceName = "COURIER_SEQ", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 100)
    private String lastName;

    @Column(name = "TOTAL_DISTANCE", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalDistance = BigDecimal.ZERO;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version = 1L;

    @Embedded
    private EmbeddedCreationAudit creationAudit = new EmbeddedCreationAudit();

    @Embedded
    private EmbeddedUpdateAudit updateAudit = new EmbeddedUpdateAudit();

    public void addTotalDistance(BigDecimal distance) {
        this.totalDistance = this.totalDistance.add(distance);
    }
}
