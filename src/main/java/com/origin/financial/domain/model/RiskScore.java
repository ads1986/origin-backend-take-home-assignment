package com.origin.financial.domain.model;

public class RiskScore {

    private static final String INELIGIBLE = "ineligible";
    private static final String ECONOMIC = "economic";
    private static final String REGULAR = "regular";
    private static final String RESPONSIBLE = "responsible";

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
        for (House house : customer.getHouses()) {
            if (house.isMortgaged())
                score += value;
        }
    }

    public void addWhenOnlyOneHouse(int value){
        if (customer.getHouses().size() == 1)
            score += value;
    }

    public void addWhenHasDependents(int value){
        if (customer.hasDependentes())
            score += value;
    }

    public void addWhenAutoHasLastFiveYears(int value){
        for (Vehicle vehicle : customer.getVehicles()) {
            if(vehicle.hasAutoLastFiveYears())
                score += value;
        }
    }

    public void addWhenOnlyOneVehicle(int value){
        if (customer.getVehicles().size() == 1)
            score += value;
    }

    public void addWhenIsMarried(int value){
        if (customer.isMarried())
            score += value;
    }

    public void addWhenDomesticPartinership(int value){
        if (customer.isDomesticPartnership())
            score += value;
    }

    public void removeWhenDomesticPartnership(int value){
        if (customer.isDomesticPartnership())
            score -= value;
    }

    public boolean isIneligible() {
        return ineligible;
    }

    public int getScore() {
        return score;
    }

    public String getProfile(){
        if (this.ineligible)
            return INELIGIBLE;

        if (this.score <= 0)
            return ECONOMIC;

        if (this.score == 1 || this.score == 2)
            return REGULAR;

        return RESPONSIBLE;
    }
}