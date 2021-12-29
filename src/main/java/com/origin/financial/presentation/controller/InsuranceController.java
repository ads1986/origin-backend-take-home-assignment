package com.origin.financial.presentation.controller;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.RiskProfile;
import com.origin.financial.domain.usecase.*;
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
    private ProcessInsurance process;

    @PostMapping("/insurance/profile")
    public RiskProfileResponse generate(@Valid @RequestBody CustomerRequest request){
        Customer customer = request.toRequest();
        RiskProfile riskProfile = process.process(customer);
        return RiskProfileResponse.builder()
                .auto(riskProfile.getAuto())
                .disability(riskProfile.getDisability())
                .home(riskProfile.getHome())
                .life(riskProfile.getLife())
                .umbrella(riskProfile.getUmbrella())
                .rent(riskProfile.getRent())
                .build();
    }
}