package com.origin.financial.domain.usecase;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.RiskScore;

public interface BaseProcessInsurance {

    String INELIGIBLE = "ineligible";
    String ECONOMIC = "economic";
    String REGULAR = "regular";
    String RESPONSIBLE = "responsible";

    RiskScore calculate(Customer info);

    default String process(Customer info){
        RiskScore riskScore = this.calculate(info);

        if (riskScore.isIneligible())
            return INELIGIBLE;

        if (riskScore.getScore() <= 0)
            return ECONOMIC;

        if (riskScore.getScore() == 1 || riskScore.getScore() == 2)
            return REGULAR;

        return RESPONSIBLE;
    }
}