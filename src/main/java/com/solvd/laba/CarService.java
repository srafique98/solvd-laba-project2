package com.solvd.laba;

import com.solvd.laba.location.Location;
import com.solvd.laba.people.Customer;
import com.solvd.laba.serviceManagement.Service;
import com.solvd.laba.people.Employee;
import com.solvd.laba.serviceManagement.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarService {

    private static final Logger LOGGER = LogManager.getLogger(CarService.class);
    private Map<String, Customer> customers; //A map of customers email/ID to their information.
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

    public Customer findCustomerWithMostServicesRequested() {
        Map<Customer, Integer> serviceCounts = new HashMap<>();
        for (Customer customer : this.customers.values()) {
            int customerServiceCount = 0;
            for (Service service : customer.getservices()) {
                customerServiceCount++;
            }
            serviceCounts.put(customer, customerServiceCount);
        }
        Map.Entry<Customer, Integer> maxEntry = null;
        for (Map.Entry<Customer, Integer> entry : serviceCounts.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        if (maxEntry != null) {
            Customer maxCustomer = maxEntry.getKey();
            int maxCount = maxEntry.getValue();
            return maxCustomer;
        }
        return null;
    }

    public List<Vehicle> getCustomerVehicles(String customerId) {
        List<Vehicle> customerVehicles = new ArrayList<>();
        if (customers.containsKey(customerId)) {
            Customer customer = customers.get(customerId);
            Map<String, Vehicle> vehicles = customer.getVehicles();
            customerVehicles.addAll(vehicles.values());
        }

        return customerVehicles;
    }
}
