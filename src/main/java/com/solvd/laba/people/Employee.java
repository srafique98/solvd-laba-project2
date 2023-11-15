package com.solvd.laba.people;

import com.solvd.laba.interfaces.Displayable;
import com.solvd.laba.interfaces.Ratable;
import com.solvd.laba.location.Location;

import java.util.Objects;

public class Employee extends Person implements Displayable, Ratable {
    private Location location;
    private String jobTittle;
    private double salary;
    private double ratings;

    public Employee(String fName, String lName, Location location, String jobTittle, double salary) {
        super(fName,lName);
        this.location = location;
        this.jobTittle = jobTittle;
        this.salary = salary;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getJobTittle() {
        return jobTittle;
    }

    public void setJobTittle(String jobTittle) {
        this.jobTittle = jobTittle;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String getInfo() {
        return "Employee Details:: " + this.toString();
    }

    @Override
    public double getRating() {
        return this.ratings;
    }

    @Override
    public void rate(double newRating) {
        System.out.println("Rating the employee with a new rating of " + newRating);
        this.ratings = newRating;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "location=" + location +
                ", jobTittle='" + jobTittle + '\'' +
                ", salary=" + salary +
                ", ratings=" + ratings +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Double.compare(getSalary(), employee.getSalary()) == 0 && Double.compare(ratings, employee.ratings) == 0 && Objects.equals(getLocation(), employee.getLocation()) && Objects.equals(getJobTittle(), employee.getJobTittle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLocation(), getJobTittle(), getSalary(), ratings);
    }
}
