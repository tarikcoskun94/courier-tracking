package com.migrosone.couriertracking.entity.courier;

import com.migrosone.couriertracking.entity.EmbeddedCreationAudit;
import com.migrosone.couriertracking.entity.EmbeddedUpdateAudit;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Data
@Entity
@Table(name = "COURIER_DRIVE")
@EntityListeners(AuditingEntityListener.class)
public class CourierDrive implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COURIER_DRIVE_SEQ_GEN")
    @SequenceGenerator(name = "COURIER_DRIVE_SEQ_GEN", sequenceName = "COURIER_DRIVE_SEQ", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "COURIER_ID", updatable = false, nullable = false)
    private Long courierId;

    @Column(name = "STARTER_GEO_SIGNAL_ID", updatable = false, nullable = false)
    private Long starterGeoSignalId;

    @Column(name = "FINISHER_GEO_SIGNAL_ID")
    private Long finisherGeoSignalId;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version = 1L;

    @Embedded
    private EmbeddedCreationAudit creationAudit = new EmbeddedCreationAudit();

    @Embedded
    private EmbeddedUpdateAudit updateAudit = new EmbeddedUpdateAudit();

    public static CourierDrive start(Long courierId, Long startGeoSignalId) {
        CourierDrive courierDrive = new CourierDrive();
        courierDrive.setCourierId(courierId);
        courierDrive.setStarterGeoSignalId(startGeoSignalId);
        return courierDrive;
    }

    public void finish(Long finisherGeoSignalId) {
        this.finisherGeoSignalId = finisherGeoSignalId;
    }

    public boolean isFinished() {
        return finisherGeoSignalId != null;
    }

    public boolean isNotFinished() {
        return !isFinished();
    }
}
