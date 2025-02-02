package com.migrosone.couriertracking.response.courier;

import com.migrosone.couriertracking.dto.courier.CourierDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetAllCouriersResponse {

    private List<CourierDTO> allCouriers;

    public static GetAllCouriersResponse of(List<CourierDTO> allCouriers) {
        return new GetAllCouriersResponse(allCouriers);
    }
}
