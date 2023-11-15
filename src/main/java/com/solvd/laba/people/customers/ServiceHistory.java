package com.solvd.laba.people.customers;

import com.solvd.laba.serviceManagement.Appointment;
import com.solvd.laba.Cost;

import java.time.LocalDate;
import java.util.LinkedList;

public class ServiceHistory {
    private Appointment appointment;
    private String serviceDescription;
    private Cost totalCost;
    private LinkedList<Service> serviceHistory;

    public ServiceHistory(Appointment appointment, String serviceDescription, Cost totalCost) {
        this.appointment = appointment;
        this.serviceDescription = serviceDescription;
        this.totalCost = totalCost;
    }

    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }
    public String getServiceDescription() { return serviceDescription; }
    public void setServiceDescription(String serviceDescription) { this.serviceDescription = serviceDescription; }


    public boolean isServiceValid() {
        // Check if the service date is in the past
        if (appointment.getDate().isBefore(LocalDate.now())) {
            return false;
        }
        if (totalCost.getTotalCost() < 0) {
            return false;
        }
        return true;
    }
}
