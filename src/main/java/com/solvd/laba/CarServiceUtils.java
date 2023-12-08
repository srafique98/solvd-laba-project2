package com.solvd.laba;

import com.solvd.laba.location.Location;
import com.solvd.laba.people.Customer;
import com.solvd.laba.people.Employee;
import com.solvd.laba.people.Person;
import com.solvd.laba.serviceManagement.Appointment;
import com.solvd.laba.serviceManagement.Service;
import com.solvd.laba.storage.Inventory;
import com.solvd.laba.storage.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.*;

public class CarServiceUtils {
    private static final Logger LOGGER = LogManager.getLogger(Location.class);
    public static void writeCustomerToFile(Customer customer) {
        customer.writeToFile();
    }

    public static Customer readCustomerFromFile() {
        return Customer.readFromFile();
    }

    public static void printPersonDetails(Person person) {
        LOGGER.info(person.getInfo());
    }

    public static void printCustomerAppointments(Customer customer) {
        LOGGER.info("Customer Appointments:");
        if (customer.getAppointments().isEmpty()) {
            LOGGER.warn("No appointments scheduled.");
        } else {
            customer.getAppointments().stream()
                    .forEach(appointment -> LOGGER.info(appointment));
        }
    }
    public static void printAllCustomers(CarService carService) {
        carService.getCustomers().forEach(customer -> LOGGER.info(customer.toString()));
    }

    public static void printEmployeeRatings(Employee employee) {
        LOGGER.info("Employee Ratings:");
        LOGGER.info(employee.getFullName() + ": " + employee.getRating());
    }

    public static void printServiceDetails(Service service) {
        LOGGER.info("Service Details:");
        LOGGER.info(service);
    }

    public static void registerCustomer(CarService carService) {
        Scanner scanner = new Scanner(System.in);
        String firstName, lastName, customerId, phoneNumber;
        do {
            System.out.println("Enter first name: ");
            firstName = scanner.nextLine();
        } while (firstName.isBlank());
        do {
            System.out.println("Enter last name: ");
            lastName = scanner.nextLine();
        } while (lastName.isBlank());
        do {
            System.out.println("Enter email:  (Format: ____@____.___)");
            customerId = scanner.nextLine();
        } while (!isValidEmail(customerId));
        do {
            System.out.println("Enter phone number (10 digits): ");
            phoneNumber = scanner.nextLine();
        } while (!isValidPhoneNumber(phoneNumber));

        Customer newCustomer = new Customer(firstName, lastName, Set.of(phoneNumber));
        writeCustomerToFile(newCustomer);
        carService.getCustomers().add(newCustomer);
        System.out.println("Customer registered successfully!");
    }

    private static boolean isValidEmail(String email) {
        Predicate<String> isValidEmail = e -> {
            int atIndex = e.indexOf('@');
            int dotIndex = e.lastIndexOf('.');
            return atIndex > 0 && dotIndex > atIndex && dotIndex + 1 < e.length();
        };
        return isValidEmail.test(email);
    }
    private static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.length() == 10 && phoneNumber.chars().allMatch(Character::isDigit);
    }

    public static void makeAppointment(List<Customer> customers, CarService carService, Inventory inventory) {
        IntStream.range(0, customers.size())
                .forEach(i -> System.out.println((i + 1) + ". " + customers.get(i).getFullName()));

        System.out.println((customers.size()+1) + ". New user");
        int customerChoice = getUserChoice(customers.size()+1);
        if (customerChoice == customers.size()+1 ) {
            registerCustomer(carService);
        }
        Customer selectedCustomer = customers.get(customerChoice - 1);

        Scanner scanner = new Scanner(System.in);
        inventory.printLowStockPartsWithInventoryCheck(3);
        if (inventory.getTotalCount() == 0){
            LOGGER.error("No parts in inventory");
            return;
        }
        inventory.removeDamagedParts();
        LOGGER.info("Removed all damaged parts from inventory");
        System.out.println("Available Services:");
        IntStream.range(0, carService.getServices().size())
                .forEach(i -> System.out.println((i + 1) + ". " + carService.getServices().get(i).getName()));

        int serviceChoice = getUserChoice(carService.getServices().size());
        Service selectedService = carService.getServices().get(serviceChoice - 1);
        switch (selectedService.getName()) {
            case "Brake Repair":
                if (!inventory.hasPart.check(inventory, "Brake Pads")) {
                    inventory.checkAmount("Brake Pads");
                    System.out.println("Insufficient stock of Brake Pads. Please try a different service.");
                    return;
                }
                break;
            case "Oil Change":
                if (!inventory.hasPart.check(inventory, "Oil Filter")) {
                    inventory.checkAmount("Oil Filter");
                    System.out.println("Insufficient stock of Oil Filters. Please try a different service.");
                    return;
                }
                break;
            case "Tire Change":
                if (!inventory.hasPart.check(inventory, "Tires")) {
                    inventory.checkAmount("Tires");
                    System.out.println("Insufficient stock of Oil Filters. Please try a different service.");
                    return;
                }
                break;
        }


        System.out.println("Available Appointment Dates:");
        List<LocalDate> availableDates = getAvailableDates();
        IntStream.range(0, availableDates.size())
                .forEach(i -> System.out.println((i + 1) + ". " + availableDates.get(i)));
        int dateChoice = getUserChoice(availableDates.size());
        LocalDate selectedDate = availableDates.get(dateChoice - 1);

        System.out.println("Available Appointment Times:");
        List<LocalTime> availableTimes = getAvailableTimes();
        IntStream.range(0, availableTimes.size())
                .forEach(i -> System.out.println((i + 1) + ". " + availableTimes.get(i)));

        int timeChoice = getUserChoice(availableTimes.size());
        LocalTime selectedTime = availableTimes.get(timeChoice - 1);
        try {
//            customer.scheduleAppointment(selectedDate, selectedTime, selectedService);
            System.out.println("Appointment scheduled successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            // Schedule appointment
            selectedCustomer.scheduleAppointment(selectedDate, selectedTime); //selectedService
            System.out.println("Appointment scheduled successfully! Confirmation details:");
            printCustomerAppointments(selectedCustomer);
            System.out.println("Customer: " + selectedCustomer.getFullName());
            System.out.println("Service: " + selectedService.getName());
            System.out.println("Date: " + selectedDate);
            System.out.println("Time: " + selectedTime);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static List<LocalDate> getAvailableDates() {
        // should i add more logic?? ask?
        List<LocalDate> dates = new ArrayList<>();
        dates.add(LocalDate.now().plusDays(1));
        dates.add(LocalDate.now().plusDays(2));
        dates.add(LocalDate.now().plusDays(3));
        return dates;
    }

    private static List<LocalTime> getAvailableTimes() {
        // should i add more logic?? ask?
        List<LocalTime> times = new ArrayList<>();
        times.add(LocalTime.of(9, 0));
        times.add(LocalTime.of(11, 0));
        times.add(LocalTime.of(14, 0));
        return times;
    }

    private static int getUserChoice(int maxChoice) {
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        do {
            System.out.println("Enter the number of your choice (1-" + maxChoice + "):");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            userChoice = scanner.nextInt();
        } while (userChoice < 1 || userChoice > maxChoice);
        return userChoice;
    }

}
