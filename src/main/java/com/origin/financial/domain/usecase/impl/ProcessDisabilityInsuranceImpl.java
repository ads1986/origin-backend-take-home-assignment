package com.origin.financial.domain.usecase.impl;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.RiskScore;
import com.origin.financial.domain.usecase.ProcessDisabilityInsurance;

import javax.inject.Named;

@Named
public class ProcessDisabilityInsuranceImpl implements ProcessDisabilityInsurance {

    @Override
    public RiskScore calculate(Customer customer) {
        RiskScore riskScore = new RiskScore(customer);

        riskScore.ineligibleWhenHasNoIncome();
        riskScore.ineligibleWhenHasAgeAboveSixty();
        riskScore.removeWhenIncomeAboveExpected(1);
        riskScore.removeWhenAgeBelowThirty(2);
        riskScore.removeWhenAgeBetweenThirtyAndForty(1);
        riskScore.addWhenHasMortgaged(1);
        riskScore.addWhenHasDependents(1);
        riskScore.removeWhenIsMarried(1);

        return riskScore;
    }

}