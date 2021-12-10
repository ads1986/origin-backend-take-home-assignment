package com.origin.financial.domain.model;

public class RiskScore {

    private int score;
    private boolean ineligible;
    private Customer customer;

    public RiskScore(Customer customer) {
        this.customer = customer;
    }

    public void ineligibleWhenHasNoHouse(){
        if (customer.hasNoHouse())
            this.ineligible = true;
    }

    public void ineligibleWhenHasNoIncome(){
        if (customer.hasNoIncome())
            this.ineligible = true;
    }

    public void ineligibleWhenHasAgeAboveSixty(){
        if (customer.hasAgeAboveSixty())
            this.ineligible = true;
    }

    public void ineligibleWhenHasNoVehicle(){
        if (customer.hasNoVehicle())
            this.ineligible = true;
    }

    public void removeWhenIncomeAboveExpected(int value){
        if (customer.hasIncomeAboveExpected())
            score -= value;
    }

    public void removeWhenAgeBelowThirty(int value){
        if (customer.hasAgeBelowThirty())
            score -= value;
    }

    public void removeWhenAgeBetweenThirtyAndForty(int value){
        if (customer.hasAgeBetweenThirtyAndForty())
            score -= value;
    }

    public void removeWhenIsMarried(int value){
        if (customer.isMarried())
            score -= value;
    }

    public void addWhenHasMortgaged(int value){
        if (customer.getHouse() != null && customer.getHouse().isMortgaged())
            score += value;
    }

    public void addWhenHasDependents(int value){
        if (customer.hasDependentes())
            score += value;
    }

    public void addWhenAutoHasLastFiveYears(int value){
        if (customer.getVehicle() != null && customer.getVehicle().hasAutoLastFiveYears())
            score += value;
    }

    public void addWhenIsMarried(int value){
        if (customer.isMarried())
            score += value;
    }

    public boolean isIneligible() {
        return ineligible;
    }

    public int getScore() {
        return score;
    }
}