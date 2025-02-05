package com.migrosone.couriertracking.controller.courier;

import com.migrosone.couriertracking.request.courier.SaveCourierGeoSignalRequest;
import com.migrosone.couriertracking.service.courier.CourierGeoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CourierGeoControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockitoBean
    public CourierGeoService service;

    @Test
    void givenProperRequest_whenStartDrive_thenReturnStatus200() throws Exception {
        doNothing().when(service).startDrive(any(SaveCourierGeoSignalRequest.class));

        final String requestBody = """
                    {
                      "courierId": 5,
                      "location": {
                        "latitude": 40.98992133009554,
                        "longitude": 29.12197657770705
                      },
                      "time": "1900-01-01T00:00:00.000000Z"
                    }
                """;

        mockMvc.perform(
                        post("/couriers/start-drive")
                                .contentType("application/json")
                                .content(requestBody)
                )
                .andExpect(status().isOk());
    }

    @Test
    void givenProperRequest_whenReceiveCourierGeoSignal_thenReturnStatus200() throws Exception {
        doNothing().when(service).receiveCourierGeoSignal(any(SaveCourierGeoSignalRequest.class));

        final String requestBody = """
                    {
                      "courierId": 5,
                      "location": {
                        "latitude": 40.98992133009554,
                        "longitude": 29.12197657770705
                      },
                      "time": "2025-02-05T02:36:38.881Z"
                    }
                """;

        mockMvc.perform(
                        post("/couriers/geo-signal")
                                .contentType("application/json")
                                .content(requestBody)
                )
                .andExpect(status().isOk());
    }

    @Test
    void givenProperRequest_whenFinishDrive_thenReturnStatus200() throws Exception {
        doNothing().when(service).finishDrive(any(SaveCourierGeoSignalRequest.class));

        final String requestBody = """
                    {
                      "courierId": 5,
                      "location": {
                        "latitude": 40.98992133009554,
                        "longitude": 29.12197657770705
                      },
                      "time": "2100-12-31T23:59:59.000000Z"
                    }
                """;

        mockMvc.perform(
                        post("/couriers/geo-signal")
                                .contentType("application/json")
                                .content(requestBody)
                )
                .andExpect(status().isOk());
    }
}