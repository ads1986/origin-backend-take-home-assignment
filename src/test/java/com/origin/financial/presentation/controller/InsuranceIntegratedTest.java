package com.origin.financial.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.origin.financial.domain.usecase.CalculateAutoInsurance;
import com.origin.financial.domain.usecase.CalculateDisabilityInsurance;
import com.origin.financial.domain.usecase.CalculateHomeInsurance;
import com.origin.financial.domain.usecase.CalculateLifeInsurance;
import com.origin.financial.presentation.request.CustomerRequest;
import com.origin.financial.presentation.request.HouseRequest;
import com.origin.financial.presentation.request.VehicleRequest;
import com.origin.financial.presentation.response.RiskProfileResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static com.origin.financial.domain.model.House.MORTGAGED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InsuranceIntegratedTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CalculateHomeInsurance homeInsurance;

    @Autowired
    private CalculateAutoInsurance autoInsurance;

    @Autowired
    private CalculateLifeInsurance lifeInsurance;

    @Autowired
    private CalculateDisabilityInsurance disabilityInsurance;

    @Test
    void requestToken() throws Exception {
        CustomerRequest request = new CustomerRequest();

        request.setAge(35);
        request.setDependents(2);
        request.setHouse(new HouseRequest(MORTGAGED));
        request.setIncome(0d);
        request.setMaritalStatus("married");
        request.setRiskQuestions(List.of(0,1,0));
        request.setVehicle(new VehicleRequest(2016));

        MvcResult result = mockMvc.perform(post("/insurance/profile")
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(request)))
                            .andExpect(status().isOk())
                            .andReturn();

        String response = result.getResponse().getContentAsString();

        RiskProfileResponse riskProfileResponse = objectMapper.readValue(response, RiskProfileResponse.class);

        assertEquals(riskProfileResponse.getAuto(), "economic");
        assertEquals(riskProfileResponse.getDisability(), "ineligible");
        assertEquals(riskProfileResponse.getHome(), "economic");
        assertEquals(riskProfileResponse.getLife(), "economic");
    }

}