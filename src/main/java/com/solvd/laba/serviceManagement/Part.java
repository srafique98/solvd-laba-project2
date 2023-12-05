package com.solvd.laba.serviceManagement;

import com.solvd.laba.enums.PartCondition;
import com.solvd.laba.interfaces.Repairable;
import com.solvd.laba.interfaces.InventoryManageable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Part implements Repairable {
    private static final Logger LOGGER = LogManager.getLogger(Part.class);
    private String name; // this is our unique identifier for Part
    private String description;
    private String manufacturer;
    private double price;
    private PartCondition condition;
    private int quantity;

    public Part(String name, double price) {
        this.name = name;
        this.price = price;
        LOGGER.info("Part: " + name + ": " + price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PartCondition getCondition() {
        return condition;
    }

    public void setCondition(PartCondition condition) {
        this.condition = condition;
    }

    @Override
    public void repair() {
        if (isDamaged()) {
            this.condition = PartCondition.USED;
            LOGGER.info(this.name + " has been repaired and is now in good condition.");
        } else {
            LOGGER.info(this.name + " is not damaged and does not require repair.");
        }
    }

    @Override
    public boolean isDamaged() {
        if (this.condition == PartCondition.DAMAGED){
            LOGGER.info("Part is damaged");
            return true;
        }
        LOGGER.info("Part is NOT damaged: " + getCondition());
        return false;
    }

    @Override
    public String toString() {
        return "Part{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", condition='" + condition + '\'' +
                '}';
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    public void substractQuantity(int amount) {
        this.quantity -= amount;
    }

}
