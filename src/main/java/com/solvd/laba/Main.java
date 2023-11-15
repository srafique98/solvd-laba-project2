package com.solvd.laba;

import com.solvd.laba.billing.Cost;
import com.solvd.laba.exceptions.AppointmentConflictException;
import com.solvd.laba.exceptions.InvalidAppointmentException;
import com.solvd.laba.exceptions.NoAppointmentException;
import com.solvd.laba.location.Location;
import com.solvd.laba.people.Customer;
import com.solvd.laba.people.Employee;
import com.solvd.laba.serviceManagement.Appointment;
import com.solvd.laba.serviceManagement.Part;
import com.solvd.laba.serviceManagement.Service;
import com.solvd.laba.serviceManagement.Vehicle;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Map<String, Vehicle> vehicles = new HashMap<>();
        LocalDate manufactureDate1 = LocalDate.of(2018, 1, 1);
        boolean isDamaged1 = false;
        Vehicle vehicle1 = new Vehicle(LocalDate.of(2018, 1, 1), "Camry", "Toyota", manufactureDate1, isDamaged1, null);
        vehicles.put("CAABC123", vehicle1);

        LocalDate manufactureDate2 = LocalDate.of(2020, 1, 1);
        boolean isDamaged2 = true;
        Vehicle vehicle2 = new Vehicle(LocalDate.of(2020, 1, 1), "Civic", "Honda", manufactureDate2, isDamaged2, null);
        vehicles.put("CAABC456", vehicle2);

        HashSet<String> phoneNumbers = new HashSet<>();
        phoneNumbers.add("123-456-7890");
        phoneNumbers.add("987-654-3210");
        List<Appointment> appointments = new ArrayList<>();

        Appointment appointment1 = new Appointment(LocalDate.of(2023, 10, 4), LocalTime.of(9, 0));
        appointment1.setStatus("Scheduled");
        appointments.add(appointment1);

        Appointment appointment2 = new Appointment(LocalDate.of(2023, 10, 5), LocalTime.of(10, 0));
        appointment2.setStatus("Completed");
        appointments.add(appointment2);

        LinkedList<Service> services = new LinkedList<>();
        Part part1 = new Part("Brake Pads", 50.00);
        Part part2 = new Part("Brake Rotors", 100.00);
        List<Part> parts = new ArrayList<>();
        parts.add(part1);
        parts.add(part2);
        Service service1 = new Service("Brake Repair", parts, new Cost(200.00, "USD"), "Scheduled");
        services.add(service1);

        Part part3 = new Part("Oil Filter", 10.00);
        List<Part> parts2 = new ArrayList<>();
        parts2.add(part3);
        Service service2 = new Service("Oil Change", parts2, new Cost(30.00, "USD"), "Completed");
        services.add(service2);

        Customer customer1 = new Customer("John", "Doe", vehicles, phoneNumbers, appointments, services);

        System.out.println(vehicle1);
        System.out.println(vehicle2);
        System.out.println(appointment1);
        System.out.println(appointment1);

        System.out.println(part1);
        System.out.println(part2);
        System.out.println(service1);
        System.out.println(service2);

        System.out.println(customer1);
    }
}
