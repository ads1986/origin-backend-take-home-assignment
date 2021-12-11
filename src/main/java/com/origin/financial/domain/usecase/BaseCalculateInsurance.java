package com.origin.financial.domain.usecase;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.RiskScore;

public interface BaseCalculateInsurance {
    RiskScore calculate(Customer info);
}