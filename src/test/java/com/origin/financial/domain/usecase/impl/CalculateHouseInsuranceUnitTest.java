package com.origin.financial.domain.usecase.impl;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.House;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.origin.financial.domain.model.House.MORTGAGED_STATUS;
import static org.assertj.core.api.Assertions.assertThat;

public class CalculateHouseInsuranceUnitTest {

    @Test
    @DisplayName("Insurance Score | Test Case 1 - Expected : [Ineligible]")
    void processScoreTest1(){
        Customer customer = Customer.builder().build();
        String result = new ProcessHomeInsuranceImpl().process(customer);
        assertThat(result).isEqualTo("ineligible");
    }

    @Test
    @DisplayName("Insurance Score | Test Case 2 - Expected : [Economic]")
    void processScoreTest2(){
        Customer customer = Customer.builder().income(200000d).age(31).house(new House(MORTGAGED_STATUS)).build();
        String result = new ProcessHomeInsuranceImpl().process(customer);
        assertThat(result).isEqualTo("economic");
    }

    @Test
    @DisplayName("Insurance Score | Test Case 3 - Expected : [Economic]")
    void processScoreTest3(){
        Customer customer = Customer.builder().income(200001d).age(29).house(new House(MORTGAGED_STATUS)).build();
        String result = new ProcessHomeInsuranceImpl().process(customer);
        assertThat(result).isEqualTo("economic");
    }

    @Test
    @DisplayName("Insurance Score | Test Case 4 - Expected : [Regular]")
    void processScoreTest4(){
        Customer customer = Customer.builder().income(20000d).age(60).house(new House(MORTGAGED_STATUS)).build();
        String result = new ProcessHomeInsuranceImpl().process(customer);
        assertThat(result).isEqualTo("regular");
    }

}