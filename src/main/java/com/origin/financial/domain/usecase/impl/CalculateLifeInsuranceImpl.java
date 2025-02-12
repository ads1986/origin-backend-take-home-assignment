package com.origin.financial.domain.usecase.impl;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.RiskScore;
import com.origin.financial.domain.usecase.CalculateLifeInsurance;

import javax.inject.Named;

@Named
public class CalculateLifeInsuranceImpl implements CalculateLifeInsurance {

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