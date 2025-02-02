package com.migrosone.couriertracking.response.store;

import com.migrosone.couriertracking.dto.store.StoreDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetAllStoresResponse {

    private List<StoreDTO> allStores;

    public static GetAllStoresResponse of(List<StoreDTO> allStores) {
        return new GetAllStoresResponse(allStores);
    }
}
