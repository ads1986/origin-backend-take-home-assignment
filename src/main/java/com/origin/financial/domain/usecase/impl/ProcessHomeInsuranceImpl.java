package com.origin.financial.domain.usecase.impl;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.RiskScore;
import com.origin.financial.domain.usecase.ProcessHomeInsurance;
import org.springframework.stereotype.Component;

@Component
public class ProcessHomeInsuranceImpl implements ProcessHomeInsurance {

    @Override
    public RiskScore calculate(Customer customer) {
        RiskScore riskScore = new RiskScore(customer);

        riskScore.ineligibleWhenHasNoHouse();
        riskScore.removeWhenIncomeAboveExpected(1);
        riskScore.removeWhenAgeBelowThirty(2);
        riskScore.removeWhenAgeBetweenThirtyAndForty(1);
        riskScore.addWhenHasMortgaged(1);

        return riskScore;
    }

}