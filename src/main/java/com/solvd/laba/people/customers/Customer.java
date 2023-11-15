package com.solvd.laba.people.customers;

import com.solvd.laba.serviceManagement.Appointment;
import com.solvd.laba.people.Person;
import com.solvd.laba.serviceManagement.Vehicle;
import com.solvd.laba.exceptions.AppointmentConflictException;
import com.solvd.laba.exceptions.InvalidAppointmentException;
import com.solvd.laba.exceptions.NoAppointmentException;
import com.solvd.laba.interfaces.Displayable;
import com.solvd.laba.interfaces.Scheduleable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Customer extends Person implements Displayable, Scheduleable {

    private Map<String, Vehicle> vehicles; // Assuming a map of car registration number to Car objects
    private HashSet<String> phoneNumbers; // unique phone number only!
    private List<Appointment> appointments;
    private List<Service> serviceHistory; // replacing invoice

    public Customer(String firstName, String lastName, HashSet<String> phoneNumbers) {
        super(firstName, lastName);
        this.phoneNumbers = phoneNumbers;
        this.vehicles = new HashMap<>();
        this.serviceHistory = new ArrayList<>();
        this.phoneNumbers = new HashSet<>();
    }

    public Customer(String firstName, String lastName, Map<String, Vehicle> vehicles, HashSet<String> phoneNumbers, List<Appointment> appointments, List<Service> serviceHistory) {
        super(firstName, lastName);
        this.vehicles = vehicles;
        this.phoneNumbers = phoneNumbers;
        this.appointments = appointments;
        this.serviceHistory = serviceHistory;
    }



    @Override
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String getInfo() {
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

    public List<Service> getServiceHistory() {
        return serviceHistory;
    }

    public void setServiceHistory(List<Service> serviceHistory) {
        this.serviceHistory = serviceHistory;
    }

    @Override
    public void scheduleAppointment(LocalDate userDate, LocalTime userTime) {
        try {
            // Check if the date and time are valid
            if (!isValidDateAndTime(userDate, userTime)) {
                throw new InvalidAppointmentException("Invalid date or time format");
            }

            // Check if the customer has a conflict with the appointment
            if (hasAppointmentConflict(userDate, userTime)) {
                throw new AppointmentConflictException("Customer already has an appointment at that time");
            }

            // Create a new appointment object and add it to the customer's list of appointments
            Appointment newAppointment = new Appointment(userDate, userTime);
            appointments.add(newAppointment);

            System.out.println("Appointment scheduled successfully for " + userDate + " at " + userTime);
        } catch (InvalidAppointmentException | AppointmentConflictException e) {
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
            // Check if the customer has any appointments
            if (appointments.isEmpty()) {
                throw new NoAppointmentException("Customer has no appointments to cancel");
            }

            Appointment mostRecentAppointment = appointments.get(appointments.size() - 1);
            appointments.remove(mostRecentAppointment);
            System.out.println("Appointment canceled successfully");

        } catch (NoAppointmentException e) {
            System.out.println(e.getMessage());
        }

    }

}
