package com.origin.financial.domain.usecase.impl;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.House;
import com.origin.financial.domain.model.RiskProfile;
import com.origin.financial.domain.usecase.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessInsuranceImpl implements ProcessInsurance {

    private static final String ECONOMIC = "economic";

    @Autowired
    private CalculateHomeInsurance home;

    @Autowired
    private CalculateAutoInsurance auto;

    @Autowired
    private CalculateLifeInsurance life;

    @Autowired
    private CalculateDisabilityInsurance disability;

    @Autowired
    private CalculateUmbrellaInsurance umbrella;

    @Autowired
    private CalculateRentInsurance rent;

    @Override
    public RiskProfile process(Customer customer) {
        RiskProfile riskProfile = new RiskProfile();
        riskProfile.setAuto(auto.calculate(customer).getProfile());
        riskProfile.setDisability(disability.calculate(customer).getProfile());
        riskProfile.setLife(life.calculate(customer).getProfile());

        for (House house : customer.getHouses()){
            if (house.isRented())
                riskProfile.setRent(rent.calculate(customer).getProfile());
            else
                riskProfile.setHome(home.calculate(customer).getProfile());
        }

        if (ECONOMIC.equals(riskProfile.getAuto()) ||
                ECONOMIC.equals(riskProfile.getDisability()) ||
                    ECONOMIC.equals(riskProfile.getHome()) ||
                        ECONOMIC.equals(riskProfile.getLife()) ||
                            ECONOMIC.equals(riskProfile.getRent())) {
            riskProfile.setUmbrella(umbrella.calculate(customer).getProfile());
        }

        return riskProfile;
    }

}