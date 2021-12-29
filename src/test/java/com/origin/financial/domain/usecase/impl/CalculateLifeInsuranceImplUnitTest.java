package com.origin.financial.domain.usecase.impl;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.House;
import com.origin.financial.domain.model.RiskScore;
import com.origin.financial.domain.usecase.BaseCalculateInsurance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.origin.financial.domain.model.Customer.*;
import static com.origin.financial.domain.model.House.OWNED;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class CalculateLifeInsuranceImplUnitTest {

    private BaseCalculateInsurance insurance;

    @BeforeEach
    void init(){
        insurance = new CalculateLifeInsuranceImpl();
    }

    @Test
    @DisplayName("Life Insurance | Has above 60 years old")
    void processScoreTest1(){
        Customer customer = Customer.builder().age(61).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.isIneligible());
    }

    @Test
    @DisplayName("Life Insurance | Has under 60 years old and over 200k of income")
    void processScoreTest2(){
        Customer customer = Customer.builder().income(200001d).age(32).houses(singletonList(new House("1", OWNED))).dependents(1).maritalStatus(SINGLE).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(-1);
        assertThat(riskScore.getProfile()).isEqualTo("economic");
    }

    @Test
    @DisplayName("Life Insurance | Has income and has under 30 years old")
    void processScoreTest3(){
        Customer customer = Customer.builder().income(100000d).age(29).houses(singletonList(new House("1", OWNED))).maritalStatus(SINGLE).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(-2);
        assertThat(riskScore.getProfile()).isEqualTo("economic");
    }

    @Test
    @DisplayName("Life Insurance | Has income and has between 30 and 40 years old")
    void processScoreTest4(){
        Customer customer = Customer.builder().income(100000d).age(32).houses(singletonList(new House("1", OWNED))).maritalStatus(SINGLE).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(-1);
        assertThat(riskScore.getProfile()).isEqualTo("economic");
    }

    @Test
    @DisplayName("Life Insurance | Has income, under 60 years old and has dependents")
    void processScoreTest5(){
        Customer customer = Customer.builder().income(100000d).age(42).houses(singletonList(new House("1", OWNED))).maritalStatus(SINGLE).dependents(1).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(1);
        assertThat(riskScore.getProfile()).isEqualTo("regular");
    }

    @Test
    @DisplayName("Life Insurance | Has income, under 60 years old and is married")
    void processScoreTest6(){
        Customer customer = Customer.builder().income(100000d).age(42).houses(singletonList(new House("1", OWNED))).maritalStatus(MARRIED).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(1);
        assertThat(riskScore.getProfile()).isEqualTo("regular");
    }


    @Test
    @DisplayName("Life Insurance | Has income, under 60 years old and is domestic partinership")
    void processScoreTest7(){
        Customer customer = Customer.builder().income(100000d).age(42).houses(singletonList(new House("1", OWNED))).maritalStatus(DOMESTIC_PARTNERSHIP).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(1);
        assertThat(riskScore.getProfile()).isEqualTo("regular");
    }
}