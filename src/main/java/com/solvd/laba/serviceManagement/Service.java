package com.solvd.laba.serviceManagement;

import com.solvd.laba.interfaces.Chargeable;
import com.solvd.laba.billing.Cost;

import java.util.List;

public class Service implements Chargeable {
    private String name;
    private List<Part> parts;
    private Cost cost;
    private String serviceStatus; // scheduled, completed, canceled
    private static int totalServicesPerformed = 0;

    public Service(String name, Cost cost) {
        this.name = name;
        this.cost = cost;
    }

    public Service(String name, List<Part> parts, Cost cost, String serviceStatus) {
        this.name = name;
        this.parts = parts;
        this.cost = cost;
        this.serviceStatus = serviceStatus;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Part> getParts() { return parts; }
    public void setParts(List<Part> parts) { this.parts = parts; }
    public Cost getCost() { return cost; }
    public void setCost(Cost cost) { this.cost = cost; }
    public String getServiceStatus() { return serviceStatus; }
    public void setServiceStatus(String serviceStatus) { this.serviceStatus = serviceStatus;}

    public static int getTotalServicesPerformed() { return totalServicesPerformed; }

    public static void setTotalServicesPerformed(int totalServicesPerformed) {
        Service.totalServicesPerformed = totalServicesPerformed;
    }

    public void completeService() {
        if (this.serviceStatus.equals("Scheduled")) {
            this.serviceStatus = "Completed";
        } else {
            System.out.println("Service cannot be completed as it is not scheduled.");
        }
    }

    public static void printTotalServicesPerformed() {
        System.out.println("Total Services Performed: " + totalServicesPerformed);
    }

    @Override
    public double totalCharge() {
        return cost.totalCharge();
    }

    @Override
    public void applyDiscount(double discountPercentage) {
        double discountAmount = this.totalCharge() * discountPercentage;
        this.setTotalCost(this.totalCharge() - discountAmount);
    }

    public void setTotalCost(double totalCost) {
        this.cost = new Cost(totalCost, "USD");
    }


}
