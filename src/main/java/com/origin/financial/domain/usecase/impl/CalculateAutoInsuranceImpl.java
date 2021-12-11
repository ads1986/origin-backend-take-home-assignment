package com.origin.financial.domain.usecase.impl;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.RiskScore;
import com.origin.financial.domain.usecase.CalculateAutoInsurance;
import javax.inject.Named;

@Named
public class CalculateAutoInsuranceImpl implements CalculateAutoInsurance {

    @Override
    public RiskScore calculate(Customer customer) {
        RiskScore riskScore = new RiskScore(customer);

        riskScore.ineligibleWhenHasNoVehicle();
        riskScore.removeWhenIncomeAboveExpected(1);
        riskScore.removeWhenAgeBelowThirty(2);
        riskScore.removeWhenAgeBetweenThirtyAndForty(1);
        riskScore.addWhenAutoHasLastFiveYears(1);

        return riskScore;
    }

}