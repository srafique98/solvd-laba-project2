package com.solvd.laba.serviceManagement;

import com.solvd.laba.interfaces.Repairable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Part implements Repairable {
    private static final Logger LOGGER = LogManager.getLogger(Part.class);
    private String name;
    private String description;
    private String manufacturer;
    private double price;
    private String condition; // New, Used, Good, Fair, Poor

    public Part(String name, double price) {
        this.name = name;
        this.price = price;
        LOGGER.info("Part: " + name + ": " + price);
    }

    public Part(String name, String description, String manufacturer, double price, String condition) {
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.price = price;
        this.condition = condition;
        LOGGER.info("Part: " + name + ": " + description + ", " + manufacturer + ", " + price + ", " + condition);
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public void repair() {
        if (isDamaged()) {
            this.condition = "Good";
            LOGGER.info(this.name + " has been repaired and is now in good condition.");
        } else {
            LOGGER.info(this.name + " is not damaged and does not require repair.");
        }
    }

    @Override
    public boolean isDamaged() {
        if (this.condition.equals("Poor")){
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
}
