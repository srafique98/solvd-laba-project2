package com.solvd.laba.people;

import com.solvd.laba.interfaces.DisplayableName;
import com.solvd.laba.location.Location;
import com.solvd.laba.serviceManagement.Service;
import com.solvd.laba.serviceManagement.Appointment;
import com.solvd.laba.serviceManagement.Vehicle;
import com.solvd.laba.exceptions.AppointmentConflictException;
import com.solvd.laba.exceptions.InvalidAppointmentException;
import com.solvd.laba.exceptions.NoAppointmentException;
import com.solvd.laba.interfaces.Displayable;
import com.solvd.laba.interfaces.Scheduleable;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Customer extends Person implements Displayable, Scheduleable, DisplayableName {
    private static final Logger LOGGER = LogManager.getLogger(Customer.class);
    private Set<String> phoneNumbers; // unique phone number only! use SET interface able to extend application easier
    private List<Appointment> appointments;
    private  List<Service> services;
    private Map<String, Vehicle> vehicles; // registration number to Car objects

    public Customer(String firstName, String lastName, Set<String> phoneNumbers) {
        super(firstName, lastName);
        this.phoneNumbers = phoneNumbers;
        this.vehicles = new HashMap<>();
        this.services = new LinkedList<>();
        this.appointments = new ArrayList<>();
        LOGGER.info("Customer creeated: " + firstName + lastName + ": " + phoneNumbers.toString());
    }

    @Override
    public String getFullName() {
        LOGGER.info(firstName + " " + lastName);
        return firstName + " " + lastName;
    }

    @Override
    public String getInfo() {
        LOGGER.info("Customer Details: " + this.toString());
        return "Customer Details: " + this.toString();
    }

    public Map<String, Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Map<String, Vehicle> vehicles) {
        this.vehicles = vehicles;
    }


    public Set<String> getPhoneNumbers() { return phoneNumbers; }

    public void setPhoneNumbers(Set<String> phoneNumbers) { this.phoneNumbers = phoneNumbers; }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Service> getservices() {
        return services;
    }

    public void setservices(LinkedList<Service> service) {
        this.services = service;
    }

    @Override
    public void scheduleAppointment(LocalDate userDate, LocalTime userTime) {
        try {
            if (!isValidDateAndTime(userDate, userTime)) {
                LOGGER.error("Invalid date or time format");
                throw new InvalidAppointmentException("Invalid date or time format");
            }
            if (hasAppointmentConflict(userDate, userTime)) {
                LOGGER.error("Customer already has an appointment at that time");
                throw new AppointmentConflictException("Customer already has an appointment at that time");
            }
            Appointment newAppointment = new Appointment(userDate, userTime);
            appointments.add(newAppointment);

            LOGGER.info("Appointment scheduled successfully for " + userDate + " at " + userTime);
        } catch (InvalidAppointmentException | AppointmentConflictException e) {
            LOGGER.error(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public boolean isValidDateAndTime(LocalDate userDate, LocalTime userTime) {
        if (userDate.isBefore(LocalDate.now())) {
            LOGGER.warn("Invalid Date Time: " + userDate + userTime);
            return false;
        }
        LOGGER.info("Valid Date Time: " + userDate + userTime);
        return true;
    }
    public boolean hasAppointmentConflict(LocalDate userDate, LocalTime userTime) {
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(userDate) && appointment.getTime().equals(userTime)) {
                LOGGER.warn("Appointment Conflict: " + userDate + userTime);
                return true;
            }
        }
        LOGGER.info("No conflict: " + userDate + userTime);
        return false;
    }
    @Override
    public void cancelAppointment() {
        try {
            if (appointments.isEmpty()) {
                LOGGER.error("Customer has no appointments to cancel");
                throw new NoAppointmentException("Customer has no appointments to cancel");
            }
            Appointment mostRecentAppointment = appointments.get(appointments.size() - 1);
            appointments.remove(mostRecentAppointment);
            LOGGER.info("Appointment canceled successfully");

        } catch (NoAppointmentException e) {
            LOGGER.error(e.getMessage());
        }

    }

    public Vehicle getVehicleInformation(String registrationNumber) { //get --> expects to return something
        if (vehicles.containsKey(registrationNumber)) {
            Vehicle vehicle = vehicles.get(registrationNumber);
            LOGGER.info("Vehicle Information for Registration Number " + registrationNumber + ":");
            LOGGER.info("Make: " + vehicle.getMake());
            LOGGER.info("Model: " + vehicle.getModel());
            LOGGER.info("Year: " + vehicle.getModelYear());
            return vehicle;
        } else {
            LOGGER.warn("Vehicle with registration number " + registrationNumber + " not found.");
            return null;
        }
    }

    public void writeToFile() {
        String fileName = "src/main/resources/customerInfo.txt"; // no beginning / means relative path
        boolean appendToFile = new File(fileName).exists();
        try (FileWriter writer = new FileWriter(new File(fileName), appendToFile)) {
            List<String> lines = List.of(
                    "First Name: " + getFullName().split(" ")[0],
                    "Last Name: " + getFullName().split(" ")[1],
                    "Phone Number: " + getPhoneNumbers()
            );

            for (String line : lines) {
                writer.write(line + "\n");
            }
            writer.write("\n");
            LOGGER.info("Customer information written to the file successfully.");
        } catch (IOException e) {
            LOGGER.error("Error writing to the file: " + e.getMessage());
        }
    }

    public static Customer readFromFile(String fileName) {
        try {
            HashSet<String> phoneNumber = new HashSet<>();
            List<String> lines = FileUtils.readLines(new File(fileName));
            String firstName = StringUtils.substringAfter(lines.get(0),  "First Name: ");
            String lastName = StringUtils.substringAfter(lines.get(0),  "Last Name: ");
            phoneNumber.add(StringUtils.substringAfter(lines.get(1), "Phone Number: "));

            return new Customer(firstName, lastName, phoneNumber);
        } catch (IOException e) {
            LOGGER.error("Error reading from the file: " + e.getMessage());
            return null;
        }
    }


}
