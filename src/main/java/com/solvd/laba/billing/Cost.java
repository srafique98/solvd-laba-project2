package com.solvd.laba.billing;

import com.solvd.laba.exceptions.InvalidCostException;
import com.solvd.laba.exceptions.InvalidDiscountException;
import com.solvd.laba.interfaces.Chargeable;

public class Cost implements Chargeable {
    public final static double TAX_RATE = 0.03; // 3% tax
    private double laborCost;
    private double partsCost;
    private double serviceFee;
    private double totalCost;
    private String currencyType;


    public Cost(double totalCost, String currencyType) {
        this.totalCost = totalCost;
        this.currencyType = currencyType;
    }

    public Cost(double laborCost, double partsCost, double serviceFee) {
        this.laborCost = laborCost;
        this.partsCost = partsCost;
        this.serviceFee = serviceFee;
        calculateTotalCost();
    }

    public double getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(double laborCost) {
        if (laborCost < 0) {
            throw new IllegalArgumentException("Labor cost cannot be negative");
        }
        this.laborCost = laborCost;
        calculateTotalCost();
    }

    public double getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(double partsCost) {
        if (partsCost < 0) {
            throw new IllegalArgumentException("Parts cost cannot be negative");
        }
        this.partsCost = partsCost;
        calculateTotalCost();
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(double serviceFee) {
        if (serviceFee < 0) {
            throw new IllegalArgumentException("Service fee cannot be negative");
        }
        this.serviceFee = serviceFee;
        calculateTotalCost();
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void calculateTotalCost() {
        this.totalCost = laborCost + partsCost + serviceFee;
    }

    public final double calculateTotalCostWithTax() {
        return totalCost + (totalCost * TAX_RATE);
    }

    public static void printCostBreakdown(Cost cost) {
        System.out.println("Cost Breakdown:");
        System.out.println("Labor Cost: $" + cost.getLaborCost());
        System.out.println("Parts Cost: $" + cost.getPartsCost());
        System.out.println("Service Fee: $" + cost.getServiceFee());
        System.out.println("Total Cost: $" + cost.getTotalCost());
    }

    // Exception handling using try-catch block
    public void validateCost() throws InvalidCostException {
        if (laborCost < 0 || partsCost < 0 || serviceFee < 0) {
            throw new InvalidCostException("Cost cannot be negative");
        }
    }

    public void calculateCostWithDiscount(double discountRate) throws InvalidDiscountException {
        if (discountRate < 0 || discountRate > 1) {
            throw new InvalidDiscountException("Invalid discount rate: " + discountRate);
        }

        double discountedCost = totalCost * (1 - discountRate);
        this.totalCost = discountedCost;
    }

    @Override
    public double totalCharge() {
        return getTotalCost();
    }

    @Override
    public void applyDiscount(double discountPercentage) {
        try {
            calculateCostWithDiscount(discountPercentage);
        } catch (InvalidDiscountException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Cost{" +
                "laborCost=" + laborCost +
                ", partsCost=" + partsCost +
                ", serviceFee=" + serviceFee +
                ", totalCost=" + totalCost +
                ", currencyType='" + currencyType + '\'' +
                '}';
    }
}
