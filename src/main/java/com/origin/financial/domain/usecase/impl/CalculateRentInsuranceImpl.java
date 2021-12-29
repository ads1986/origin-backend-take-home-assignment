package com.origin.financial.domain.usecase.impl;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.RiskScore;
import com.origin.financial.domain.usecase.CalculateHomeInsurance;
import com.origin.financial.domain.usecase.CalculateRentInsurance;

import javax.inject.Named;

@Named
public class CalculateRentInsuranceImpl implements CalculateRentInsurance {

    @Override
    public RiskScore calculate(Customer customer) {
        RiskScore riskScore = new RiskScore(customer);

        riskScore.removeWhenIncomeAboveExpected(1);
        riskScore.removeWhenAgeBelowThirty(2);
        riskScore.removeWhenAgeBetweenThirtyAndForty(1);

        return riskScore;
    }

}