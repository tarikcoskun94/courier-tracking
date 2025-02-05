package com.migrosone.couriertracking.controller.store;

import com.migrosone.couriertracking.dto.location.PointDTO;
import com.migrosone.couriertracking.dto.store.StoreDTO;
import com.migrosone.couriertracking.service.store.StoreQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class StoreQueryControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockitoBean
    public StoreQueryService service;

    @Test
    void whenGetAllStores_thenReturnListOfStores() throws Exception {
        final PointDTO point1 = new PointDTO();
        point1.setLatitude(40.98992133009554);
        point1.setLongitude(30.98992133009554);

        final PointDTO point2 = new PointDTO();
        point2.setLatitude(40.98992133009554);
        point2.setLongitude(30.98992133009554);

        final StoreDTO store1 = new StoreDTO();
        store1.setId(100L);
        store1.setName("Forum Bornova Migros 5M");
        store1.setLocation(point1);
        store1.setCreatorUser("CR_USER1");
        store1.setCreationDate(LocalDateTime.of(2025, 1, 1, 0, 0, 0, 0));
        store1.setUpdaterUser("UP_USER1");
        store1.setUpdateDate(LocalDateTime.of(2026, 1, 1, 0, 0, 0, 0));

        final StoreDTO store2 = new StoreDTO();
        store2.setId(200L);
        store2.setName("Karşıyaka Migros MMM");
        store2.setLocation(point2);
        store2.setCreatorUser("CR_USER2");
        store2.setCreationDate(LocalDateTime.of(2025, 1, 1, 0, 0, 0, 0));
        store2.setUpdaterUser("UP_USER2");
        store2.setUpdateDate(LocalDateTime.of(2026, 1, 1, 0, 0, 0, 0));

        when(service.getAllStores()).thenReturn(List.of(store1, store2));

        mockMvc.perform(get("/stores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.allStores").isArray())
                .andExpect(jsonPath("$.allStores.length()").value(2))

                .andExpect(jsonPath("$.allStores[0].id").value(100))
                .andExpect(jsonPath("$.allStores[0].name").value("Forum Bornova Migros 5M"))
                .andExpect(jsonPath("$.allStores[0].location.latitude").value(40.98992133009554))
                .andExpect(jsonPath("$.allStores[0].location.longitude").value(30.98992133009554))
                .andExpect(jsonPath("$.allStores[0].creatorUser").value("CR_USER1"))
                .andExpect(jsonPath("$.allStores[0].creationDate").value("2025-01-01T00:00:00.000000"))
                .andExpect(jsonPath("$.allStores[0].updaterUser").value("UP_USER1"))
                .andExpect(jsonPath("$.allStores[0].updateDate").value("2026-01-01T00:00:00.000000"))

                .andExpect(jsonPath("$.allStores[1].id").value(200))
                .andExpect(jsonPath("$.allStores[1].name").value("Karşıyaka Migros MMM"))
                .andExpect(jsonPath("$.allStores[1].location.latitude").value(40.98992133009554))
                .andExpect(jsonPath("$.allStores[1].location.longitude").value(30.98992133009554))
                .andExpect(jsonPath("$.allStores[1].creatorUser").value("CR_USER2"))
                .andExpect(jsonPath("$.allStores[1].creationDate").value("2025-01-01T00:00:00.000000"))
                .andExpect(jsonPath("$.allStores[1].updaterUser").value("UP_USER2"))
                .andExpect(jsonPath("$.allStores[1].updateDate").value("2026-01-01T00:00:00.000000"));
    }
}