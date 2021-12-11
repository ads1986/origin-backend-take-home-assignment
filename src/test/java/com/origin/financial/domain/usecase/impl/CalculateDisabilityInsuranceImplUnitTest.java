package com.origin.financial.domain.usecase.impl;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.House;
import com.origin.financial.domain.model.RiskScore;
import com.origin.financial.domain.usecase.BaseCalculateInsurance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.origin.financial.domain.model.Customer.MARRIED;
import static com.origin.financial.domain.model.Customer.SINGLE;
import static org.assertj.core.api.Assertions.assertThat;

public class CalculateDisabilityInsuranceImplUnitTest {

    private BaseCalculateInsurance insurance;

    @BeforeEach
    void init(){
        insurance = new CalculateDisabilityInsuranceImpl();
    }

    @Test
    @DisplayName("Disability Insurance | Has no income")
    void processScoreTest1(){
        Customer customer = Customer.builder().income(0d).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.isIneligible());
    }

    @Test
    @DisplayName("Disability Insurance | Has above 60 years old")
    void processScoreTest2(){
        Customer customer = Customer.builder().income(100d).age(61).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.isIneligible());
    }

    @Test
    @DisplayName("Disability Insurance | Has under 60 years old and over 200k of income")
    void processScoreTest3(){
        Customer customer = Customer.builder().income(200001d).age(32).house(new House(House.OWNED)).dependents(1).maritalStatus(SINGLE).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(-1);
        assertThat(riskScore.getProfile()).isEqualTo("economic");
    }

    @Test
    @DisplayName("Disability Insurance | Has income and has under 30 years old")
    void processScoreTest4(){
        Customer customer = Customer.builder().income(100000d).age(29).house(new House(House.OWNED)).maritalStatus(SINGLE).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(-2);
        assertThat(riskScore.getProfile()).isEqualTo("economic");
    }

    @Test
    @DisplayName("Disability Insurance | Has income and has between 30 and 40 years old")
    void processScoreTest5(){
        Customer customer = Customer.builder().income(100000d).age(32).house(new House(House.OWNED)).maritalStatus(SINGLE).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(-1);
        assertThat(riskScore.getProfile()).isEqualTo("economic");
    }

    @Test
    @DisplayName("Disability Insurance | Has income, under 60 years old and has a house mortgaged")
    void processScoreTest6(){
        Customer customer = Customer.builder().income(100000d).age(42).house(new House(House.MORTGAGED)).maritalStatus(SINGLE).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(1);
        assertThat(riskScore.getProfile()).isEqualTo("regular");
    }


    @Test
    @DisplayName("Disability Insurance | Has income, under 60 years old and has dependents")
    void processScoreTest7(){
        Customer customer = Customer.builder().income(100000d).age(42).house(new House(House.OWNED)).maritalStatus(SINGLE).dependents(1).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(1);
        assertThat(riskScore.getProfile()).isEqualTo("regular");
    }

    @Test
    @DisplayName("Disability Insurance | Has income, under 60 years old and is married")
    void processScoreTest8(){
        Customer customer = Customer.builder().income(100000d).age(42).house(new House(House.OWNED)).maritalStatus(MARRIED).build();
        RiskScore riskScore = insurance.calculate(customer);
        assertThat(riskScore.getScore()).isEqualTo(-1);
        assertThat(riskScore.getProfile()).isEqualTo("economic");
    }
}