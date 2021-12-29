package com.origin.financial.domain.usecase.impl;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.RiskScore;
import com.origin.financial.domain.usecase.CalculateHomeInsurance;

import javax.inject.Named;

@Named
public class CalculateHomeInsuranceImpl implements CalculateHomeInsurance {

    @Override
    public RiskScore calculate(Customer customer) {
        RiskScore riskScore = new RiskScore(customer);

        riskScore.ineligibleWhenHasNoHouse();
        riskScore.removeWhenIncomeAboveExpected(1);
        riskScore.removeWhenAgeBelowThirty(2);
        riskScore.removeWhenAgeBetweenThirtyAndForty(1);
        riskScore.addWhenHasMortgaged(1);
        riskScore.addWhenOnlyOneHouse(1);

        return riskScore;
    }

}