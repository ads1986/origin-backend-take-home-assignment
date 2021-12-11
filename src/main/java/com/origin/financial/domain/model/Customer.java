package com.origin.financial.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Customer {

    public static final String MARRIED = "married";
    public static final String SINGLE = "single";

    private static Integer AGE_THIRTY = 30;
    private static Integer AGE_FORTY = 40;
    private static Integer AGE_SIXTY = 60;
    private static Double INCOME_ABOVE_EXPECTED = 200000d;

    private int age;
    private int dependents;
    private House house;
    private Double income;
    private String maritalStatus;
    private List<Integer> riskQuestions;
    private Vehicle vehicle;

    public Boolean isMarried(){
        return maritalStatus == MARRIED;
    }

    public Boolean hasDependentes(){
        return this.dependents >= 1;
    }

    public Boolean hasAgeBelowThirty(){
        return this.age < AGE_THIRTY;
    }

    public Boolean hasAgeAboveSixty(){return this.age > AGE_SIXTY; }

    public Boolean hasAgeBetweenThirtyAndForty(){
        return this.age >= AGE_THIRTY && this.age <= AGE_FORTY;
    }

    public Boolean hasIncomeAboveExpected(){
        return this.income != null && this.income > INCOME_ABOVE_EXPECTED;
    }

    public Boolean hasNoIncome(){
        return this.income == null || this.income <= 0;
    }

    public Boolean hasNoHouse(){
        return this.house == null;
    }

    public Boolean hasNoVehicle(){
        return this.vehicle == null;
    }

}