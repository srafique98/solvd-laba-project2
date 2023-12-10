package com.solvd.laba;

import com.solvd.laba.location.Location;
import com.solvd.laba.people.Customer;
import com.solvd.laba.serviceManagement.Service;
import com.solvd.laba.people.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CarService {

    private static final Logger LOGGER = LogManager.getLogger(CarService.class);
    private List<Customer> customers;
    private List<Employee> employees;
    private List<Service> services;
    private List<Location> locations;


    public CarService(List<Service> services, List<Location> locations) {
        this.services = services;
        this.locations = locations;
        this.employees = new ArrayList<>();
        this.customers = new ArrayList<>();
        LOGGER.info("Card Service has been created! Card Service Info: " + toString());
    }

    public CarService() {
        LOGGER.warn("No car service has been created: " + toString());
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }



    @Override
    public String toString() {
        return "CarService{" +
                "customers=" + customers +
                ", employees=" + employees +
                ", services=" + services +
                ", locations=" + locations +
                '}';
    }

}


