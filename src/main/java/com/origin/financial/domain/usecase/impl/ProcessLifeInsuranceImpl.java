package com.origin.financial.domain.usecase.impl;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.RiskScore;
import com.origin.financial.domain.usecase.ProcessLifeInsurance;
import org.springframework.stereotype.Component;

@Component
public class ProcessLifeInsuranceImpl implements ProcessLifeInsurance {

    @Override
    public RiskScore calculate(Customer customer) {
        RiskScore riskScore = new RiskScore(customer);

        riskScore.ineligibleWhenHasAgeAboveSixty();
        riskScore.removeWhenIncomeAboveExpected(1);
        riskScore.removeWhenAgeBelowThirty(2);
        riskScore.removeWhenAgeBetweenThirtyAndForty(1);
        riskScore.addWhenHasDependents(1);
        riskScore.addWhenIsMarried(1);

        return riskScore;
    }

}