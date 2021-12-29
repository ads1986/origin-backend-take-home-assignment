package com.origin.financial.domain.usecase.impl;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.House;
import com.origin.financial.domain.model.RiskScore;
import com.origin.financial.domain.usecase.BaseCalculateInsurance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.origin.financial.domain.model.House.OWNED;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class CalculateUmbrellaInsuranceUnitTest {

    private BaseCalculateInsurance insurance;

    @BeforeEach
    void init(){
        insurance = new CalculateUmbrellaInsuranceImpl();
    }

    @Test
    @DisplayName("Home Insurance | Has a house and has over 200k of income")
    void processScoreTest2(){
        Customer customer = Customer.builder().income(200001d).age(42).houses(singletonList(new House("1", OWNED))).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(-1);
        assertThat(riskScore.getProfile()).isEqualTo("economic");
    }

    @Test
    @DisplayName("Home Insurance | Has a house and has under 30 years old")
    void processScoreTest3(){
        Customer customer = Customer.builder().income(100000d).age(29).houses(singletonList(new House("1", OWNED))).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(-2);
        assertThat(riskScore.getProfile()).isEqualTo("economic");
    }

    @Test
    @DisplayName("Home Insurance | Has a house and has between 30 and 40 years old")
    void processScoreTest4(){
        Customer customer = Customer.builder().income(20000d).age(32).houses(singletonList(new House("1", OWNED))).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(-1);
        assertThat(riskScore.getProfile()).isEqualTo("economic");
    }

}