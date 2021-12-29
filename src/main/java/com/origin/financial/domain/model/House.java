package com.origin.financial.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class House {

    public static final String RENTED = "rented";
    public static final String MORTGAGED = "mortgaged";
    public static final String OWNED = "owned";

    private String id;
    private String ownershipStatus;

    public Boolean isMortgaged(){
        return MORTGAGED.equals(this.ownershipStatus);
    }

    public Boolean isRented(){
        return RENTED.equals(this.ownershipStatus);
    }
}
