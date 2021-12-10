package com.origin.financial.domain.usecase.impl;

import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.House;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.origin.financial.domain.model.Customer.MARRIED_STATUS;
import static org.assertj.core.api.Assertions.assertThat;

public class ProcessDisabilityInsuranceImplUnitTest {

    @Test
    @DisplayName("Insurance Score | Test Case 1 - Expected : [Ineligible]")
    void processScoreTest1(){
        Customer customer = Customer.builder().income(0d).build();
        String result = new ProcessDisabilityInsuranceImpl().process(customer);
        assertThat(result).isEqualTo("ineligible");
    }

    @Test
    @DisplayName("Insurance Score | Test Case 2 - Expected : [Ineligible]")
    void processScoreTest2(){
        Customer customer = Customer.builder().income(100d).age(61).build();
        String result = new ProcessDisabilityInsuranceImpl().process(customer);
        assertThat(result).isEqualTo("ineligible");
    }

    @Test
    @DisplayName("Insurance Score | Test Case 3 - Expected : [Economic]")
    void processScoreTest3(){
        Customer customer = Customer.builder().income(200001d).age(29).house(new House(House.MORTGAGED_STATUS)).dependents(1).maritalStatus(MARRIED_STATUS).build();
        String result = new ProcessDisabilityInsuranceImpl().process(customer);
        assertThat(result).isEqualTo("economic");
    }

    @Test
    @DisplayName("Insurance Score | Test Case 4 - Expected : [Economic]")
    void processScoreTest4(){
        Customer customer = Customer.builder().income(200000d).age(50).house(new House(House.OWNED_STATUS)).maritalStatus(MARRIED_STATUS).build();
        String result = new ProcessDisabilityInsuranceImpl().process(customer);
        assertThat(result).isEqualTo("economic");
    }

    @Test
    @DisplayName("Insurance Score | Test Case 5 - Expected : [Economic]")
    void processScoreTest5(){
        Customer customer = Customer.builder().income(20000d).age(60).house(new House(House.MORTGAGED_STATUS)).maritalStatus(MARRIED_STATUS).build();
        String result = new ProcessDisabilityInsuranceImpl().process(customer);
        assertThat(result).isEqualTo("economic");
    }

    @Test
    @DisplayName("Insurance Score | Test Case 6 - Expected : [Economic]")
    void processScoreTest6(){
        Customer customer = Customer.builder().income(200001d).age(31).house(new House(House.MORTGAGED_STATUS)).dependents(1).maritalStatus(MARRIED_STATUS).build();
        String result = new ProcessDisabilityInsuranceImpl().process(customer);
        assertThat(result).isEqualTo("economic");
    }

}