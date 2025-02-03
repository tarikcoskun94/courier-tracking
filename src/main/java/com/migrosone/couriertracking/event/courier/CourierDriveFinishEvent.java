package com.migrosone.couriertracking.event.courier;

import com.migrosone.couriertracking.entity.courier.CourierDrive;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CourierDriveFinishEvent extends ApplicationEvent {

    private final CourierDrive finishedDrive;

    private CourierDriveFinishEvent(Object source, CourierDrive finishedDrive) {
        super(source);
        this.finishedDrive = finishedDrive;
    }

    public static CourierDriveFinishEvent of(Object source, CourierDrive finishedDrive) {
        return new CourierDriveFinishEvent(source, finishedDrive);
    }

    public Long getCourierId() {
        return finishedDrive.getCourierId();
    }
}
