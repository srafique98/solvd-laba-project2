package com.solvd.laba.people;

import com.solvd.laba.location.Location;
import com.solvd.laba.serviceManagement.Service;
import com.solvd.laba.serviceManagement.Appointment;
import com.solvd.laba.serviceManagement.Vehicle;
import com.solvd.laba.exceptions.AppointmentConflictException;
import com.solvd.laba.exceptions.InvalidAppointmentException;
import com.solvd.laba.exceptions.NoAppointmentException;
import com.solvd.laba.interfaces.Displayable;
import com.solvd.laba.interfaces.Scheduleable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Customer extends Person implements Displayable, Scheduleable {
    private static final Logger LOGGER = LogManager.getLogger(Customer.class);
    private HashSet<String> phoneNumbers; // unique phone number only!
    private List<Appointment> appointments;
    private  List<Service> services;
    private Map<String, Vehicle> vehicles; // registration number to Car objects

    public Customer(String firstName, String lastName, HashSet<String> phoneNumbers) {
        super(firstName, lastName);
        this.phoneNumbers = phoneNumbers;
        this.vehicles = new HashMap<>();
        this.services = new LinkedList<>();
        this.appointments = new ArrayList<>();
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

    public HashSet<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(HashSet<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

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

            System.out.println("Appointment scheduled successfully for " + userDate + " at " + userTime);
        } catch (InvalidAppointmentException | AppointmentConflictException e) {
            LOGGER.error(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public boolean isValidDateAndTime(LocalDate userDate, LocalTime userTime) {
        if (userDate.isBefore(LocalDate.now())) {
            return false;
        }
        return true;
    }
    public boolean hasAppointmentConflict(LocalDate userDate, LocalTime userTime) {
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(userDate) && appointment.getTime().equals(userTime)) {
                return true;
            }
        }
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
            System.out.println("Appointment canceled successfully");

        } catch (NoAppointmentException e) {
            LOGGER.error(e.getMessage());
            System.out.println(e.getMessage());
        }

    }

    public void getVehicleInformation(String registrationNumber) {
        if (vehicles.containsKey(registrationNumber)) {
            Vehicle vehicle = vehicles.get(registrationNumber);
            System.out.println("Vehicle Information for Registration Number " + registrationNumber + ":");
            System.out.println("Make: " + vehicle.getMake());
            System.out.println("Model: " + vehicle.getModel());
            System.out.println("Year: " + vehicle.getModelYear());
        } else {
            System.out.println("Vehicle with registration number " + registrationNumber + " not found.");
        }
    }


}
