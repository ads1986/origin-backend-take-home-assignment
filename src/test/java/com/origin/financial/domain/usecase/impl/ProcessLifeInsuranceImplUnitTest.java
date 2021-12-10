package com.origin.financial.domain.usecase.impl;

import com.origin.financial.domain.model.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProcessLifeInsuranceImplUnitTest {

    private static String MARRIED_STATUS = "married";

    @Test
    @DisplayName("Insurance Score | Test Case 1 - Expected : [Ineligible]")
    void processScoreTest1(){
        Customer customer = Customer.builder().age(61).build();
        String result = new ProcessLifeInsuranceImpl().process(customer);
        assertThat(result).isEqualTo("ineligible");
    }

    @Test
    @DisplayName("Insurance Score | Test Case 2 - Expected : [Economic]")
    void processScoreTest2(){
        Customer customer = Customer.builder().income(200001d).age(29).dependents(1).maritalStatus(MARRIED_STATUS).build();
        String result = new ProcessLifeInsuranceImpl().process(customer);
        assertThat(result).isEqualTo("economic");
    }

    @Test
    @DisplayName("Insurance Score | Test Case 3 - Expected : [Economic]")
    void processScoreTest3(){
        Customer customer = Customer.builder().income(200001d).age(31).dependents(1).maritalStatus(MARRIED_STATUS).build();
        String result = new ProcessLifeInsuranceImpl().process(customer);
        assertThat(result).isEqualTo("economic");
    }

}