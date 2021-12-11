package com.origin.financial.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class House {

    public static final String MORTGAGED = "mortgaged";
    public static final String OWNED = "single";

    private String ownershipStatus;

    public Boolean isMortgaged(){
        return this.ownershipStatus == MORTGAGED;
    }
}
