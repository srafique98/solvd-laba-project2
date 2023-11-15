package com.solvd.laba;

import com.solvd.laba.location.Location;
import com.solvd.laba.people.Customer;
import com.solvd.laba.serviceManagement.Service;
import com.solvd.laba.people.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarService {

    private static final Logger LOGGER = LogManager.getLogger(CarService.class);
    private Map<String, Customer> customers; //A map of customers to their information.
    private List<Employee> employees;
    private List<Service> services;
    private List<Location> locations;


    public CarService(List<Service> services, List<Location> locations) {
        this.services = services;
        this.locations = locations;
        this.employees = new ArrayList<>();
        this.customers = new HashMap<>();
    }
    public CarService(Map<String, Customer> customers, List<Employee> employees, List<Service> services, List<Location> locations) {
        this.customers = customers;
        this.employees = employees;
        this.services = services;
        this.locations = locations;
        LOGGER.info("Card Service has been created! Card Service Info: " + toString());
    }

    public CarService() {
        LOGGER.warn("No car service has been created: " + toString());
    }

    public Map<String, Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Map<String, Customer> customers) {
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
