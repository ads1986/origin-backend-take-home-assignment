package com.origin.financial.presentation.controller;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.usecase.ProcessAutoInsurance;
import com.origin.financial.domain.usecase.ProcessDisabilityInsurance;
import com.origin.financial.domain.usecase.ProcessHomeInsurance;
import com.origin.financial.domain.usecase.ProcessLifeInsurance;
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
    private ProcessHomeInsurance homeInsurance;

    @Autowired
    private ProcessAutoInsurance autoInsurance;

    @Autowired
    private ProcessLifeInsurance lifeInsurance;

    @Autowired
    private ProcessDisabilityInsurance disabilityInsurance;

    @PostMapping("/insurance/profile")
    public RiskProfileResponse generate(@Valid @RequestBody CustomerRequest request){
        Customer customer = request.toRequest();
        return RiskProfileResponse.builder()
                .auto(autoInsurance.process(customer))
                .disability(disabilityInsurance.process(customer))
                .home(homeInsurance.process(customer))
                .life(lifeInsurance.process(customer))
                .build();
    }
}
