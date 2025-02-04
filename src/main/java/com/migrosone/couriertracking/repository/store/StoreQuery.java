package com.migrosone.couriertracking.repository.store;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class StoreQuery {

    public static final String FIND_BY_POINT_IN_RADIUS = """
                SELECT *
                FROM STORE S
                WHERE ST_DWithin(S.LOCATION, ST_SetSRID(ST_MakePoint(:lon, :lat), 4326), :r)
            """;
}
