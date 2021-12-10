package com.origin.financial.domain.usecase.impl;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.Vehicle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProcessAutoInsuranceImplUnitTest {

    @Test
    @DisplayName("Insurance Score | Test Case 1 - Expected : [Ineligible]")
    void processScoreTest1(){
        Customer customer = Customer.builder().build();
        String result = new ProcessAutoInsuranceImpl().process(customer);
        assertThat(result).isEqualTo("ineligible");
    }

    @Test
    @DisplayName("Insurance Score | Test Case 2 - Expected : [Economic]")
    void processScoreTest2(){
        Customer customer = Customer.builder().income(200001d).age(31).vehicle(new Vehicle(2005)).build();
        String result = new ProcessAutoInsuranceImpl().process(customer);
        assertThat(result).isEqualTo("economic");
    }

    @Test
    @DisplayName("Insurance Score | Test Case 3 - Expected : [Economic]")
    void processScoreTest3(){
        Customer customer = Customer.builder().income(200001d).age(29).vehicle(new Vehicle(2005)).build();
        String result = new ProcessAutoInsuranceImpl().process(customer);
        assertThat(result).isEqualTo("economic");
    }

    @Test
    @DisplayName("Insurance Score | Test Case 4 - Expected : [Regular]")
    void processScoreTest4(){
        Customer customer = Customer.builder().income(20000d).age(60).vehicle(new Vehicle(2016)).build();
        String result = new ProcessAutoInsuranceImpl().process(customer);
        assertThat(result).isEqualTo("regular");
    }

}