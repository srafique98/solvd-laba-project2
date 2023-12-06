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
            for (Appointment appointment : customer.getAppointments()) {
                LOGGER.info(appointment);
            }
        }
    }
    public static void printAllCustomers(CarService carService) {
        for (Customer customer : carService.getCustomers()) {
            LOGGER.info(customer.toString());
        }
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

    public static void makeAppointment(Customer customer, CarService carService) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available Services:");
        for (int i = 0; i < carService.getServices().size(); i++) {
            Service service = carService.getServices().get(i);
            System.out.println((i + 1) + ". " + service.getName());
        }
        int serviceChoice = getUserChoice(carService.getServices().size());
        Service selectedService = carService.getServices().get(serviceChoice - 1);

        System.out.println("Available Appointment Dates:");
        List<LocalDate> availableDates = getAvailableDates();
        for (int i = 0; i < availableDates.size(); i++) {
            System.out.println((i + 1) + ". " + availableDates.get(i));
        }
        int dateChoice = getUserChoice(availableDates.size());
        LocalDate selectedDate = availableDates.get(dateChoice - 1);

        System.out.println("Available Appointment Times:");
        List<LocalTime> availableTimes = getAvailableTimes();
        for (int i = 0; i < availableTimes.size(); i++) {
            System.out.println((i + 1) + ". " + availableTimes.get(i));
        }

        int timeChoice = getUserChoice(availableTimes.size());
        LocalTime selectedTime = availableTimes.get(timeChoice - 1);
        try {
//            customer.scheduleAppointment(selectedDate, selectedTime, selectedService);
            System.out.println("Appointment scheduled successfully!");
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
