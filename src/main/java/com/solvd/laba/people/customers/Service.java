package com.solvd.laba.people.customers;

import com.solvd.laba.serviceManagement.Appointment;
import com.solvd.laba.Cost;
import com.solvd.laba.interfaces.Scheduleable;
import com.solvd.laba.people.employees.Employee;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

//rename to serviceRequest
public class Service implements Scheduleable {
    private String name;
    private Employee serviceProvider;
    private Customer customer;
    private LinkedList<ServiceHistory> serviceHistory;
    private Appointment appointment;
    private Cost price; // when status is completed put into serviceHistory!!
    private String serviceStatus; // scheduled, completed, canceled
    private static int totalServicesPerformed = 0;

    public Service(Cost servicePrice, Employee serviceProvider, Customer customer, Appointment appointment) {
        this.price = servicePrice;
        this.serviceProvider = serviceProvider;
        this.customer = customer;
        this.serviceHistory = new LinkedList<>();
        this.appointment = appointment;
        this.serviceStatus = "scheduled";
        totalServicesPerformed++;
    }

    public Service(String name, Employee serviceProvider, Customer customer, LinkedList<ServiceHistory> serviceHistory, Appointment appointment, Cost costs, String serviceStatus) {
        this.name = name;
        this.serviceProvider = serviceProvider;
        this.customer = customer;
        this.serviceHistory = serviceHistory;
        this.appointment = appointment;
        this.price = costs;
        this.serviceStatus = serviceStatus;
    }

    @Override
    public void scheduleAppointment(LocalDate userDate, LocalTime userTime) {

    }

    @Override
    public void cancelAppointment() {

    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Cost getServicePrice() { return price; }
    public void setServicePrice(Cost servicePrice) { this.price = servicePrice; }
    public Employee getServiceProvider() { return serviceProvider; }
    public void setServiceProvider(Employee serviceProvider) { this.serviceProvider = serviceProvider; }
    public Customer getCustomer() { return this.customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }


    public void completeService() {
        if (this.serviceStatus.equals("Scheduled")) {
            this.serviceStatus = "Completed";
            ServiceHistory serviceHistoryItem = new ServiceHistory(this.appointment, this.name, this.price);
            this.serviceHistory.add(serviceHistoryItem);
        } else {
            System.out.println("Service cannot be completed as it is not scheduled.");
        }
    }

    public static void printTotalServicesPerformed() {
        System.out.println("Total Services Performed: " + totalServicesPerformed);
    }

    //    public Service searchServiceByName(String name) {
//        for (Service service : this.services) {
//            if (service.getName().equals(name)) {
//                return service;
//            }
//        }
//
//        return null;
//    }
}
