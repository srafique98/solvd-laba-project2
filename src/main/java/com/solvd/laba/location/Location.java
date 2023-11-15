package com.solvd.laba.location;

import com.solvd.laba.interfaces.Displayable;
import com.solvd.laba.interfaces.Ratable;

import java.time.LocalDate;

public class Location implements Ratable, Displayable {
    private String city;
    private String country;
    private String branchName;
    private LocalDate establishedDate;
    private double ratings;
    private static int locationCount = 0;

    static { // this is Static block
        locationCount = 0; }

    public Location(String city, String country, String branchName) {
        this.city = city;
        this.country = country;
        this.branchName = branchName;
        locationCount++;
    }

    public Location(String city, String country, String branchName, LocalDate establishedDate, double ratings) {
        this.city = city;
        this.country = country;
        this.branchName = branchName;
        this.establishedDate = establishedDate;
        this.ratings = ratings;
        locationCount++;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public LocalDate getEstablishedDate() {
        return establishedDate;
    }

    public void setEstablishedDate(LocalDate establishedDate) {
        this.establishedDate = establishedDate;
    }

    public static int getLocationCount() {
        return locationCount;
    }

    public static void setLocationCount(int locationCount) {
        Location.locationCount = locationCount;
    }

    public int yearsOfService() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear() - establishedDate.getYear();
    }
    @Override
    public double getRating() {
        return this.ratings;
    }
    @Override
    public void rate(double newRating) {
        System.out.println("Rating the location with a new rating of " + newRating);
        this.ratings = newRating;
    }
    @Override
    public String getFullName() {
        return this.branchName;
    }

    @Override
    public String getInfo() {
        return this.toString();
    }

    @Override
    public String toString() {
        return "Location{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", branchName='" + branchName + '\'' +
                ", establishedDate=" + establishedDate +
                ", ratings=" + ratings +
                '}';
    }
}

