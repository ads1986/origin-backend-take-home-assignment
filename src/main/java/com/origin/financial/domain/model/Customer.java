package com.origin.financial.domain.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Customer {

    public static final String DIVORCED = "divorced";
    public static final String DOMESTIC_PARTNERSHIP = "domestic partnership";
    public static final String MARRIED = "married";
    public static final String SINGLE = "single";

    private static Integer AGE_THIRTY = 30;
    private static Integer AGE_FORTY = 40;
    private static Integer AGE_SIXTY = 60;
    private static Double INCOME_ABOVE_EXPECTED = 200000d;

    private int age;
    private int dependents;
    @Singular
    private List<House> houses;
    private Double income;
    private String maritalStatus;
    private List<Integer> riskQuestions;
    @Singular
    private List<Vehicle> vehicles;

    public Boolean isDivorced(){
        return maritalStatus == DIVORCED;
    }

    public Boolean isDomesticPartnership(){
        return maritalStatus == DOMESTIC_PARTNERSHIP;
    }

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
        return this.getHouses().size() == 0;
    }

    public Boolean hasOnlyOneHouse(){
        return this.houses.size() == 1;
    }

    public Boolean hasNoVehicle(){
        return this.getVehicles().size() == 0;
    }

    public Boolean hasOnlyOneVehicle(){
        return this.vehicles.size() == 1;
    }


}