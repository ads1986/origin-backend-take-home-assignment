package com.origin.financial.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HouseRequest {
    @Pattern(regexp = "mortgaged|owned", message = "Ownership Status must to be : mortgaged our owned")
    @JsonProperty("ownership_status")
    private String ownershipStatus;
}

