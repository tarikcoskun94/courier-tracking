package com.migrosone.couriertracking.repository.courier;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class CourierGeoSignalQuery {

    public static final String CALCULATE_DISTANCE_BETWEEN_TWO_SIGNAL_BY_COURIER_ID = """
                SELECT ST_Length(ST_MakeLine(CGS.LOCATION::geometry)::geography) / 1000 as DISTANCE_KM
                FROM COURIER_GEO_SIGNAL CGS
                WHERE CGS.COURIER_ID = :courierId
                  AND ID BETWEEN :startSignalId AND :endSignalId
                GROUP BY CGS.COURIER_ID;
            """;
}
