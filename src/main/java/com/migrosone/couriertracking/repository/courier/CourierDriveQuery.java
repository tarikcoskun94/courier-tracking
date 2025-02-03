package com.migrosone.couriertracking.repository.courier;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class CourierDriveQuery {

    public static final String EXIST_ACTIVE_DRIVE_BY_COURIER_ID = """
                SELECT EXISTS (
                    SELECT 1
                    FROM COURIER_DRIVE CD
                    WHERE CD.COURIER_ID = :courierId
                      AND CD.FINISHER_GEO_SIGNAL_ID IS NULL
                )
            """;

    public static final String FIND_ACTIVE_DRIVE_BY_COURIER_ID = """
                SELECT *
                FROM COURIER_DRIVE CD
                WHERE CD.COURIER_ID = :courierId
                  AND CD.FINISHER_GEO_SIGNAL_ID IS NULL
            """;
}
