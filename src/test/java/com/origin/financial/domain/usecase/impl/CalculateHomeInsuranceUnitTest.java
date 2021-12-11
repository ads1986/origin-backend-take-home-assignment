package com.origin.financial.domain.usecase.impl;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.House;
import com.origin.financial.domain.model.RiskScore;
import com.origin.financial.domain.usecase.BaseCalculateInsurance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.origin.financial.domain.model.House.MORTGAGED;
import static com.origin.financial.domain.model.House.OWNED;
import static org.assertj.core.api.Assertions.assertThat;

public class CalculateHomeInsuranceUnitTest {

    private BaseCalculateInsurance insurance;

    @BeforeEach
    void init(){
        insurance = new CalculateHomeInsuranceImpl();
    }

    @Test
    @DisplayName("Home Insurance | Has no house")
    void processScoreTest1(){
        Customer customer = Customer.builder().build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.isIneligible());
    }

    @Test
    @DisplayName("Home Insurance | Has a house and has over 200k of income")
    void processScoreTest2(){
        Customer customer = Customer.builder().income(200001d).age(42).house(new House(OWNED)).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(-1);
        assertThat(riskScore.getProfile()).isEqualTo("economic");
    }

    @Test
    @DisplayName("Home Insurance | Has a house and has under 30 years old")
    void processScoreTest3(){
        Customer customer = Customer.builder().income(100000d).age(29).house(new House(OWNED)).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(-2);
        assertThat(riskScore.getProfile()).isEqualTo("economic");
    }

    @Test
    @DisplayName("Home Insurance | Has a house and has between 30 and 40 years old")
    void processScoreTest4(){
        Customer customer = Customer.builder().income(20000d).age(32).house(new House(OWNED)).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(-1);
        assertThat(riskScore.getProfile()).isEqualTo("economic");
    }

    @Test
    @DisplayName("Home Insurance | Has a house with mortgaged")
    void processScoreTest5(){
        Customer customer = Customer.builder().income(20000d).age(42).house(new House(MORTGAGED)).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(1);
        assertThat(riskScore.getProfile()).isEqualTo("regular");
    }

}