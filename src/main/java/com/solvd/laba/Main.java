package com.solvd.laba;

import com.solvd.laba.billing.Cost;
import com.solvd.laba.enums.Country;
import com.solvd.laba.enums.CurrencyType;
import com.solvd.laba.enums.Status;
import com.solvd.laba.location.Location;
import com.solvd.laba.people.Customer;
import com.solvd.laba.people.Employee;
import com.solvd.laba.serviceManagement.Appointment;
import com.solvd.laba.storage.Inventory;
import com.solvd.laba.storage.Part;
import com.solvd.laba.serviceManagement.Service;
import com.solvd.laba.serviceManagement.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        Map<String, Vehicle> vehicles = new HashMap<>();
        LocalDate manufactureDate1 = LocalDate.of(2018, 1, 1);
        boolean isDamaged1 = false;
        Vehicle vehicle1 = new Vehicle(manufactureDate1, "Camry", "Toyota", null);
        vehicles.put("CAABC123", vehicle1);

        LocalDate manufactureDate2 = LocalDate.of(2020, 1, 1);
        boolean isDamaged2 = true;
        Vehicle vehicle2 = new Vehicle(manufactureDate2, "Civic", "Honda", null);
        vehicles.put("CAABC456", vehicle2);

        HashSet<String> phoneNumbers = new HashSet<>();
        phoneNumbers.add("123-456-7890");
        phoneNumbers.add("987-654-3210");
        List<Appointment> appointments = new ArrayList<>();

        Appointment appointment1 = new Appointment(LocalDate.of(2023, 10, 4), LocalTime.of(9, 0));
        appointment1.setStatus(Status.SCHEDULED);
        appointments.add(appointment1);

        Appointment appointment2 = new Appointment(LocalDate.of(2023, 10, 5), LocalTime.of(10, 0));
        appointment2.setStatus(Status.COMPLETED);
        appointments.add(appointment2);

        LinkedList<Service> services = new LinkedList<>();
        Inventory inventory = new Inventory();
        inventory.addPart("Brake Pads", 50.00, 10);
        inventory.addPart("Oil Filter", 10.00, 20);
        inventory.addPart("Tires", 100.00, 4);

        Part part1 = new Part("Brake Pads", 50.00);
        Part part2 = new Part("Brake Rotors", 100.00);
        List<Part> parts = new ArrayList<>();
        parts.add(part1);
        parts.add(part2);
        Service service1 = new Service("Brake Repair");
        service1.setServiceStatus(Status.COMPLETED);
        service1.setCost(new Cost(250.00, CurrencyType.USD));
        services.add(service1);

        Part part3 = new Part("Oil Filter", 10.00);
        List<Part> parts2 = new ArrayList<>();
        parts2.add(part3);
        Service service2 = new Service("Oil Change");
        service1.setServiceStatus(Status.COMPLETED);
        service1.setCost(new Cost(30.00, CurrencyType.USD));
        services.add(service2);

        HashSet<String> phoneNumbers2 = new HashSet<>();
        phoneNumbers.add("123-456-7890");
        phoneNumbers.add("987-654-3210");
        Customer customer1 = new Customer("Alice", "Phoebe", phoneNumbers);

        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(customer1);

        customer1.setVehicles(vehicles);
        customer1.setAppointments(appointments);
        customer1.setservices(services);

        LOGGER.info(vehicle1);
        LOGGER.info(vehicle2);
        LOGGER.info(appointment1);
        LOGGER.info(appointment1);

        LOGGER.info(part1);
        LOGGER.info(part2);
        LOGGER.info(service1);
        LOGGER.info(service2);

        LOGGER.info(customer1);

        ArrayList<Location> locations = new ArrayList<>();
        Location location1 = new Location("Sunnyvale", Country.USA, "123 Main St.");
        location1.setEstablishedDate(LocalDate.of(2015, 1, 1));
        location1.rate(4.5);
        locations.add(location1);

        Location location2 = new Location("San Francisco", Country.USA, "456 Mission St.");
        location2.setEstablishedDate(LocalDate.of(2010, 1, 1));
        location2.rate(4.8);
        locations.add(location2);

        ArrayList<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee("TooToo", "His", new Location("Sunnyvale", Country.USA, "123 Main St."), "Mechanic", 50000.00);
        employees.add(employee1);

        Employee employee2 = new Employee("Bob", "Williams", new Location("San Francisco", Country.USA, "456 Mission St."), "Technician", 40000.00);
        employees.add(employee2);

        CarService carService = new CarService(services, locations);
        carService.setCustomers(customers);
        carService.setEmployees(employees);
        LOGGER.info(carService);

        services.add(new Service("Tire Change"));
        locations.add(new Location("City", Country.USA, "Branch 1"));
        Customer fakeCustomer = new Customer("shan", "yoo", Set.of("123456789"));
        Employee fakeEmployee = new Employee("davis", "himmet", locations.get(0), "Mechanic", 50000.0);

        carService.getCustomers().add(fakeCustomer);
        carService.getEmployees().add(fakeEmployee);

        CarServiceUtils.printPersonDetails(fakeCustomer);
        CarServiceUtils.printPersonDetails(fakeEmployee);
        CarServiceUtils.makeAppointment(carService.getCustomers(), carService, inventory);

        inventory.getAllParts().keySet().stream()
                .filter(partName -> inventory.getPartQuantity(partName) == 0)
                .map(partName -> {
                    System.out.println("Removing part " + partName + " due to 0 quantity.");
                    return partName;
                })
                .collect(Collectors.toList())
                .forEach(inventory::removePart);
//        Customer readCustomer = CarServiceUtils.readCustomerFromFile();
    }

    }
