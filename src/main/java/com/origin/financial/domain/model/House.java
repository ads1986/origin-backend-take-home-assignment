package com.origin.financial.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class House {

    public static final String MORTGAGED_STATUS = "mortgaged";
    public static final String OWNED_STATUS = "single";

    private String ownershipStatus;

    public Boolean isMortgaged(){
        return this.ownershipStatus == MORTGAGED_STATUS;
    }
}
