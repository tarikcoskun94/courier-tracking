package com.migrosone.couriertracking.repository.courier;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class CourierQuery {

    public static final String FIND_TOTAL_DISTANCE_BY_ID = """
                SELECT C.TOTAL_DISTANCE
                FROM COURIER C
                WHERE C.ID = :id
            """;
}
