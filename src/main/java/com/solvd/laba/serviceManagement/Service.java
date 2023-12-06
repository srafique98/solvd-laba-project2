package com.solvd.laba.serviceManagement;

import com.solvd.laba.enums.CurrencyType;
import com.solvd.laba.enums.Status;
import com.solvd.laba.interfaces.Chargeable;
import com.solvd.laba.billing.Cost;
import com.solvd.laba.storage.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class Service implements Chargeable {
    private static final Logger LOGGER = LogManager.getLogger(Service.class);
    private String name;
    private List<Part> parts;
    private Cost cost;
    private Status serviceStatus;
    private static int totalServicesPerformed = 0;

    public Service(String name) {
        this.name = name;
        this.serviceStatus = Status.IN_PROGRESS;
        this.parts = new ArrayList<>();
        LOGGER.info("Service has been created: " + name);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Part> getParts() { return parts; }
    public void setParts(List<Part> parts) { this.parts = parts; }
    public Cost getCost() { return cost; }
    public void setCost(Cost cost) { this.cost = cost; }

    public Status getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(Status serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public static int getTotalServicesPerformed() { return totalServicesPerformed; }

    public static void setTotalServicesPerformed(int totalServicesPerformed) {
        Service.totalServicesPerformed = totalServicesPerformed;
    }

    public void completeService() {
        if (this.serviceStatus.equals(Status.SCHEDULED)) {
            LOGGER.info("Service completed: ");
            this.serviceStatus = Status.COMPLETED;
        } else {
            LOGGER.warn("Service cannot be completed as it is not scheduled.");
        }
    }

    public static void printTotalServicesPerformed() {
        LOGGER.info("Total Services Performed: " + totalServicesPerformed);
    }

    @Override
    public double totalCharge() {
        return cost.totalCharge();
    }
    @Override
    public void applyDiscount(double discountPercentage) {
        double currentTotalCharge = this.totalCharge();
        double discountedTotalCharge = applyDiscountFunction.apply(discountPercentage).apply(currentTotalCharge);
        LOGGER.info("Applying discount on service: " + discountedTotalCharge);
        this.setTotalCost(discountedTotalCharge);
    }

    public void setTotalCost(double totalCost) {
        this.cost = new Cost(totalCost, CurrencyType.USD);
    }

    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", parts=" + parts +
                ", cost=" + cost +
                ", serviceStatus='" + serviceStatus.getStatusDescription() + '\'' +
                '}';
    }

    public Function<Double, Function<Double, Double>> applyDiscountFunction =
            discountPercentage -> totalCharge -> {
                double discountAmount = totalCharge * discountPercentage;
                return totalCharge - discountAmount;
            };
}
