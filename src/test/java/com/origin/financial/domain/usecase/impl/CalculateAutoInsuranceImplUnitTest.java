package com.origin.financial.domain.usecase.impl;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.RiskScore;
import com.origin.financial.domain.model.Vehicle;
import com.origin.financial.domain.usecase.BaseCalculateInsurance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class CalculateAutoInsuranceImplUnitTest {

    private BaseCalculateInsurance insurance;

    @BeforeEach
    void init(){
        insurance = new CalculateAutoInsuranceImpl();
    }

    @Test
    @DisplayName("Auto Insurance | Has no vehicle")
    void processScoreTest1(){
        Customer customer = Customer.builder().build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.isIneligible()).isTrue();
    }

    @Test
    @DisplayName("Auto Insurance | Has a vehicle and has over 200k of income")
    void processScoreTest2(){
        Customer customer = Customer.builder().income(200001d).age(42).vehicles(singletonList(new Vehicle("1", 2005))).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(0);
        assertThat(riskScore.getProfile()).isEqualTo("economic");
    }

    @Test
    @DisplayName("Auto Insurance | Has a vehicle and has under 30 years old")
    void processScoreTest3(){
        Customer customer = Customer.builder().income(200000d).age(29).vehicles(singletonList(new Vehicle("1", 2005))).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(-1);
        assertThat(riskScore.getProfile()).isEqualTo("economic");
    }

    @Test
    @DisplayName("Auto Insurance | Has a vehicle and has between 30 and 40 years old")
    void processScoreTest4(){
        Customer customer = Customer.builder().income(200000d).age(32).vehicles(singletonList(new Vehicle("1", 2005))).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(0);
        assertThat(riskScore.getProfile()).isEqualTo("economic");
    }

    @Test
    @DisplayName("Auto Insurance | Has a vehicle produced in the last 5 years (Score 1) | Expected : [Regular]")
    void processScoreTest5(){
        Customer customer = Customer.builder().income(20000d).age(60).vehicles(singletonList(new Vehicle("1", 2016))).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(2);
        assertThat(riskScore.getProfile()).isEqualTo("regular");
    }

}