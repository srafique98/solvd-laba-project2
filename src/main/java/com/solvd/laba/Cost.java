package com.solvd.laba;

public class Cost {
    private double laborCost;
    private double partsCost;
    private double serviceFee;
    private double totalCost;

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

    // Final variable
    public final static double TAX_RATE = 0.07; // 7% sales tax

    // Final method
    public final double calculateTotalCostWithTax() {
        return totalCost + (totalCost * TAX_RATE);
    }

    // Static block
    static {
        System.out.println("Loading Cost class...");
    }

    // Static method
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
    //chargable can calculuate total charge and discount method as well!

    // Exception handling using try-with-resources block
    public void calculateCostWithDiscount(Discountable discountable) throws InvalidDiscountException {
        try (Discountable discountableResource = discountable) { // Automatically closes the discountable object
            double discountedCost = totalCost * (1 - discountableResource.getDiscountRate());
            this.totalCost = discountedCost;
        } catch (Exception e) {
            throw new InvalidDiscountException("Invalid discount");
        }
    }
}
