package com.origin.financial.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.origin.financial.domain.model.Customer;
import com.origin.financial.domain.model.House;
import com.origin.financial.domain.model.Vehicle;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class CustomerRequest {

    @NotNull(message = "Age is mandatory")
    @Min(0)
    private Integer age;

    @NotNull(message = "Dependents is mandatory")
    @Min(0)
    private Integer dependents;

    @NotNull(message = "Income is mandatory")
    @Min(0)
    private Double income;

    @Pattern(regexp = "married|single", message = "Marital Status must to be : married our single")
    @JsonProperty("marital_status")
    private String maritalStatus;

    @NotNull(message = "Risk Questions is mandatory")
    @JsonProperty("risk_questions")
    private List<Integer> riskQuestions;

    @Valid
    private HouseRequest house;

    @Valid
    private VehicleRequest vehicle;

    public Customer toRequest(){
        return Customer.builder()
                .age(this.age)
                .dependents(this.dependents)
                .house(this.house != null ? new House(this.house.getOwnershipStatus()) : null)
                .maritalStatus(this.getMaritalStatus())
                .riskQuestions(this.riskQuestions)
                .vehicle(this.vehicle != null ? new Vehicle(this.vehicle.getYear()) : null)
                .build();
    }

}