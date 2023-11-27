package com.solvd.laba;

import com.solvd.laba.people.Customer;
import com.solvd.laba.people.Employee;
import com.solvd.laba.people.Person;
import com.solvd.laba.serviceManagement.Appointment;
import com.solvd.laba.serviceManagement.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.Set;

public class CarServiceUtils {

    public static void printPersonDetails(Person person) {
        System.out.println(person.getInfo());
    }

    public static void printCustomerAppointments(Customer customer) {
        System.out.println("Customer Appointments:");
        for (Appointment appointment : customer.getAppointments()) {
            System.out.println(appointment);
        }
    }

    public static void printEmployeeRatings(Employee employee) {
        System.out.println("Employee Ratings:");
        System.out.println(employee.getFullName() + ": " + employee.getRating());
    }

    public static void printServiceDetails(Service service) {
        System.out.println("Service Details:");
        System.out.println(service);
    }

    public static void registerCustomer(CarService carService) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();

        System.out.println("Enter email/ID:");
        String customerId = scanner.nextLine();

        System.out.println("Enter phone number:");
        String phoneNumber = scanner.nextLine();

        Customer newCustomer = new Customer(firstName, lastName, Set.of(phoneNumber));
        carService.getCustomers().put(customerId, newCustomer);

        System.out.println("Customer registered successfully!");
    }

    public static void makeAppointment(Customer customer, CarService carService) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available Services:");
        for (Service service : carService.getServices()) {
            System.out.println(service.getName());
        }
        System.out.println("Enter the name of the service you want to schedule:");
        String serviceName = scanner.nextLine();
        Service selectedService = null;
        for (Service service : carService.getServices()) {
            if (service.getName().equalsIgnoreCase(serviceName)) {
                selectedService = service;
                break;
            }
        }
        if (selectedService != null) {
            System.out.println("Enter the date for the appointment (yyyy-MM-dd):");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            System.out.println("Enter the time for the appointment (HH:mm):");
            LocalTime time = LocalTime.parse(scanner.nextLine());
            try {
                customer.scheduleAppointment(date, time);
                System.out.println("Appointment scheduled successfully!");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid service name.");
        }
    }
}
