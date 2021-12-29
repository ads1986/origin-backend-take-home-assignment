package com.origin.financial.domain.usecase;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.RiskProfile;

public interface ProcessInsurance{
    RiskProfile process(Customer info);
}