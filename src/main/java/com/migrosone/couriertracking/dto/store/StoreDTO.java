package com.migrosone.couriertracking.dto.store;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.migrosone.couriertracking.dto.location.PointDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoreDTO {

    private Long id;
    private String name;
    private PointDTO location;
    private String creatorUser;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime creationDate;
    private String updaterUser;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime updateDate;
}
