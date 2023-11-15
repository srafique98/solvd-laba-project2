package com.solvd.laba;

import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {

//        // Create a list of customers.
//        List<Customer> customers = new ArrayList<>();
//        customers.add(new Customer("John Doe", "123 Main Street", "555-555-5555"));
//        customers.add(new Customer("Jane Doe", "456 Elm Street", "666-666-6666"));
//
//        // Create a list of employees.
//        List<Employee> employees = new ArrayList<>();
//        employees.add(new Employee("Bob Smith", "Mechanic", "12345"));
//        employees.add(new Employee("Jane Jones", "Secretary", "67890"));
//
//        // Create a list of services.
//        List<Service> services = new ArrayList<>();
//        services.add(new Service("Oil Change", "$49.99"));
//        services.add(new Service("Tire Rotation", "$29.99"));
//
//        // Create a list of locations.
//        List<Location> locations = new ArrayList<>();
//        locations.add(new Location("123 Main Street", "Lodi, CA 95240"));
//        locations.add(new Location("456 Elm Street", "San Francisco, CA 94102"));

        // Create a new CarService object.
//        CarService carService = new CarService(customers, employees, services, locations);

        CarService carService = new CarService();
        // Print the CarService object to the console.
        System.out.println(carService);
    }
}
