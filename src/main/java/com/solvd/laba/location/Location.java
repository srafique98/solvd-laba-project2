package com.solvd.laba.location;

import com.solvd.laba.billing.Cost;
import com.solvd.laba.exceptions.InvalidRatingException;
import com.solvd.laba.interfaces.Displayable;
import com.solvd.laba.interfaces.DisplayableName;
import com.solvd.laba.interfaces.Ratable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class Location implements Ratable, Displayable, DisplayableName {
    private static final Logger LOGGER = LogManager.getLogger(Location.class);
    private String city;
    private String country;
    private String branchName;
    private LocalDate establishedDate;
    private double rating;
    private static int locationCount;

    static { // this is Static block
        LOGGER.info("Location class has been created.");
        locationCount = 0; }


    public Location(String city, String country, String branchName) {
        LOGGER.info("A location has spurred up: " + country + ", " + city + ", " + branchName);
        this.city = city;
        this.country = country;
        this.branchName = branchName;
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
        LOGGER.info("Years of service is: " + (currentDate.getYear() - establishedDate.getYear()));
        return currentDate.getYear() - establishedDate.getYear();
    }
    @Override
    public double getRating() {
        return this.rating;
    }
    @Override
    public void rate(double newRating) {
        if (newRating < 0 || newRating > 5) {
            LOGGER.warn("Invalid rating: " + newRating);
            throw new InvalidRatingException("Invalid rating: " + newRating);
        }
        LOGGER.info("Rating the location with a new rating of " + newRating);
        this.rating = newRating;
    }

    @Override
    public String getFullName() {
        return this.getBranchName();
    }

    @Override
    public String getInfo() {
        LOGGER.info(this.toString());
        return this.toString();
    }

    public String getFullAddress() {
        return city + ", " + country;
    }
    public boolean isLocatedInCountry(String country) {
        return this.country.equals(country);
    }

    @Override
    public String toString() {
        return "Location{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", branchName='" + branchName + '\'' +
                ", establishedDate=" + establishedDate +
                ", ratings=" + rating +
                '}';
    }
}

