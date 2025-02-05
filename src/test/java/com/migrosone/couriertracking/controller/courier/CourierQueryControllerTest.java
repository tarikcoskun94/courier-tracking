package com.migrosone.couriertracking.controller.courier;

import com.migrosone.couriertracking.dto.courier.CourierDTO;
import com.migrosone.couriertracking.service.courier.CourierQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CourierQueryControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockitoBean
    public CourierQueryService service;

    @Test
    void whenGetAllCouriers_thenReturnListOfCouriers() throws Exception {
        final CourierDTO courier1 = new CourierDTO();
        courier1.setId(100L);
        courier1.setFirstName("Ali");
        courier1.setLastName("MELEK");
        courier1.setTotalDistance(BigDecimal.ONE.setScale(2, RoundingMode.HALF_UP));
        courier1.setCreatorUser("CR_USER1");
        courier1.setCreationDate(LocalDateTime.of(2025, 1, 1, 0, 0, 0, 0));
        courier1.setUpdaterUser("UP_USER1");
        courier1.setUpdateDate(LocalDateTime.of(2026, 1, 1, 0, 0, 0, 0));


        final CourierDTO courier2 = new CourierDTO();
        courier2.setId(200L);
        courier2.setFirstName("Meltem");
        courier2.setLastName("KARA");
        courier2.setTotalDistance(BigDecimal.TWO.setScale(2, RoundingMode.HALF_UP));
        courier2.setCreatorUser("CR_USER2");
        courier2.setCreationDate(LocalDateTime.of(2025, 1, 1, 0, 0, 0, 0));
        courier2.setUpdaterUser("UP_USER2");
        courier2.setUpdateDate(LocalDateTime.of(2026, 1, 1, 0, 0, 0, 0));

        when(service.getAllCouriers()).thenReturn(List.of(courier1, courier2));

        mockMvc.perform(get("/couriers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.allCouriers").isArray())
                .andExpect(jsonPath("$.allCouriers.length()").value(2))

                .andExpect(jsonPath("$.allCouriers[0].id").value(100))
                .andExpect(jsonPath("$.allCouriers[0].firstName").value("Ali"))
                .andExpect(jsonPath("$.allCouriers[0].lastName").value("MELEK"))
                .andExpect(jsonPath("$.allCouriers[0].totalDistance").value("1.00"))
                .andExpect(jsonPath("$.allCouriers[0].creatorUser").value("CR_USER1"))
                .andExpect(jsonPath("$.allCouriers[0].creationDate").value("2025-01-01T00:00:00.000000"))
                .andExpect(jsonPath("$.allCouriers[0].updaterUser").value("UP_USER1"))
                .andExpect(jsonPath("$.allCouriers[0].updateDate").value("2026-01-01T00:00:00.000000"))

                .andExpect(jsonPath("$.allCouriers[1].id").value(200))
                .andExpect(jsonPath("$.allCouriers[1].firstName").value("Meltem"))
                .andExpect(jsonPath("$.allCouriers[1].lastName").value("KARA"))
                .andExpect(jsonPath("$.allCouriers[1].totalDistance").value("2.00"))
                .andExpect(jsonPath("$.allCouriers[1].creatorUser").value("CR_USER2"))
                .andExpect(jsonPath("$.allCouriers[1].creationDate").value("2025-01-01T00:00:00.000000"))
                .andExpect(jsonPath("$.allCouriers[1].updaterUser").value("UP_USER2"))
                .andExpect(jsonPath("$.allCouriers[1].updateDate").value("2026-01-01T00:00:00.000000"));
    }

    @Test
    void givenId_whenGetTotalDistanceById_thenReturnTotalDistance() throws Exception {
        final BigDecimal totalDistance = BigDecimal.ONE.setScale(2, RoundingMode.HALF_UP);

        when(service.getTotalDistanceById(100L)).thenReturn(totalDistance);

        mockMvc.perform(get("/couriers/{id}/total-distance", 100L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("1.00"));
    }
}