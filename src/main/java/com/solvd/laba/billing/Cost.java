package com.solvd.laba.billing;

import com.solvd.laba.exceptions.InvalidCostException;
import com.solvd.laba.exceptions.InvalidDiscountException;
import com.solvd.laba.interfaces.Chargeable;
import com.solvd.laba.serviceManagement.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Cost implements Chargeable {
    public final static double TAX_RATE = 0.03; // 3% tax
    private static final Logger LOGGER = LogManager.getLogger(Cost.class);
    private double laborCost;
    private double partsCost;
    private double serviceFee;
    private double totalCost;
    private String currencyType;


    public Cost(double totalCost, String currencyType) {
        LOGGER.info("Cost class has been created: " + totalCost + " " + currencyType);
        this.totalCost = totalCost;
        this.currencyType = currencyType;
    }

    public Cost(double laborCost, double partsCost, double serviceFee) {
        LOGGER.info("Cost class has been created: " + laborCost + ", " + partsCost + ", " + serviceFee);
        this.laborCost = laborCost;
        this.partsCost = partsCost;
        this.serviceFee = serviceFee;
        calculateTotalCost();
    }
    static {
        LOGGER.warn("Cost class has been created.");
    }

    public double getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(double laborCost) {
        if (laborCost < 0) {
            LOGGER.error("Labor cost cannot be negative");
            throw new IllegalArgumentException("Labor cost cannot be negative");
        }
        this.laborCost = laborCost;
        calculateTotalCost();
        LOGGER.info("Sucessfully set Labor cost");
    }

    public double getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(double partsCost) {
        if (partsCost < 0) {
            LOGGER.error("Parts cost cannot be negative");
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
            LOGGER.error("Service fee cannot be negative");
            throw new IllegalArgumentException("Service fee cannot be negative");
        }
        this.serviceFee = serviceFee;
        calculateTotalCost();
        LOGGER.info("Sucessfully service fee");
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
        LOGGER.info("Cost Breakdown:");
        LOGGER.info("Labor Cost: $" + cost.getLaborCost());
        LOGGER.info("Parts Cost: $" + cost.getPartsCost());
        LOGGER.info("Service Fee: $" + cost.getServiceFee());
        LOGGER.info("Total Cost: $" + cost.getTotalCost());
    }

    public void validateCost() throws InvalidCostException {
        if (laborCost < 0 || partsCost < 0 || serviceFee < 0) {
            LOGGER.error("Cost cannot be negative");
            throw new InvalidCostException("Cost cannot be negative");
        }
    }

    public void calculateCostWithDiscount(double discountRate) throws InvalidDiscountException {
        if (discountRate < 0 || discountRate > 1) {
            LOGGER.error("Invalid discount rate: " + discountRate);
            throw new InvalidDiscountException("Invalid discount rate: " + discountRate);
        }

        double discountedCost = totalCost * (1 - discountRate);
        this.totalCost = discountedCost;
        LOGGER.info("Discount has been applied: " + discountedCost);
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
            LOGGER.error(e.getMessage());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cost cost = (Cost) o;
        return Double.compare(getLaborCost(), cost.getLaborCost()) == 0 && Double.compare(getPartsCost(), cost.getPartsCost()) == 0 && Double.compare(getServiceFee(), cost.getServiceFee()) == 0 && Double.compare(getTotalCost(), cost.getTotalCost()) == 0 && Objects.equals(currencyType, cost.currencyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLaborCost(), getPartsCost(), getServiceFee(), getTotalCost(), currencyType);
    }
}
