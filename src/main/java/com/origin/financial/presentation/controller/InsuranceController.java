package com.origin.financial.presentation.controller;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.usecase.CalculateAutoInsurance;
import com.origin.financial.domain.usecase.CalculateDisabilityInsurance;
import com.origin.financial.domain.usecase.CalculateHomeInsurance;
import com.origin.financial.domain.usecase.CalculateLifeInsurance;
import com.origin.financial.presentation.request.CustomerRequest;
import com.origin.financial.presentation.response.RiskProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class InsuranceController {

    @Autowired
    private CalculateHomeInsurance home;

    @Autowired
    private CalculateAutoInsurance auto;

    @Autowired
    private CalculateLifeInsurance life;

    @Autowired
    private CalculateDisabilityInsurance disability;

    @PostMapping("/insurance/profile")
    public RiskProfileResponse generate(@Valid @RequestBody CustomerRequest request){
        Customer customer = request.toRequest();
        return RiskProfileResponse.builder()
                .auto(auto.calculate(customer).getProfile())
                .disability(disability.calculate(customer).getProfile())
                .home(home.calculate(customer).getProfile())
                .life(life.calculate(customer).getProfile())
                .build();
    }
}
