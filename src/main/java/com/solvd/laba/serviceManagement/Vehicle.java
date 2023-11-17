package com.solvd.laba.serviceManagement;

import com.solvd.laba.CarService;
import com.solvd.laba.interfaces.Repairable;
import com.solvd.laba.people.Customer;
import com.solvd.laba.people.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Vehicle implements Repairable {
    private static final Logger LOGGER = LogManager.getLogger(Vehicle.class);
    private Customer owner;
    private LocalDate modelYear;
    private String model;
    private String make;
    private LocalDate manufactureDate;
    private  List<Part> damagedParts;
    private boolean isDamaged;

    public Vehicle(LocalDate modelYear, String model, String make, Customer owner) {
        this.modelYear = modelYear;
        this.model = model;
        this.make = make;
        this.owner = owner;
        this.damagedParts = new ArrayList<>();
    }

    public Person getOwner() { return owner; }
    public void setOwner(Customer owner) { this.owner = owner; }
    public LocalDate getModelYear() {
        return modelYear;
    }
    public void setModelYear(LocalDate modelYear) {
        this.modelYear = modelYear;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public LocalDate getManufactureDate() {
        return manufactureDate;
    }
    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public boolean isOlderThan(int years) {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear() - manufactureDate.getYear() > years;
    }
    public int getAge() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear() - manufactureDate.getYear();
    }
    public List<Part> getDamagedParts() {
        return damagedParts;
    }
    public void setDamagedParts(List<Part> damagedParts) {
        this.damagedParts = damagedParts;
        if (!damagedParts.isEmpty()) {
            this.isDamaged = true;
        }
    }
    public void addDamagedPart(Part damagedPart) {
        this.damagedParts.add(damagedPart);
        this.isDamaged = true;
    }
    public void removeDamagedPart(Part damagedPart) {
        this.damagedParts.remove(damagedPart);
        if (damagedParts.isEmpty()) {
            this.isDamaged = false;
        }
    }
    @Override
    public void repair() {
        if (isDamaged()) {
            System.out.println("Repairing the following damaged parts:");
            for (Part damagedPart : damagedParts) {
                damagedPart.repair();
            }
            damagedParts.clear();
            this.isDamaged = false;
            System.out.println("Vehicle repair completed.");
        } else {
            System.out.println("Vehicle is not damaged and does not require repair.");
        }
    }

    @Override
    public boolean isDamaged() {
        return this.isDamaged;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "owner=" + owner +
                ", modelYear=" + modelYear +
                ", model='" + model + '\'' +
                ", make='" + make + '\'' +
                ", manufactureDate=" + manufactureDate +
                ", damagedParts=" + damagedParts +
                ", isDamaged=" + isDamaged +
                '}';
    }
}
