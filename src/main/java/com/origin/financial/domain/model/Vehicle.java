package com.origin.financial.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static java.time.YearMonth.now;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Vehicle {

    private static Integer FIVE_YEARS = 5;

    @NotNull
    @Min(0)
    private int year;

    public Boolean hasAutoLastFiveYears(){
        return (now().getYear() - this.year) <= FIVE_YEARS;
    }
}
