package com.solvd.laba.serviceManagement;

import com.solvd.laba.enums.Status;
import com.solvd.laba.interfaces.Scheduleable;
import com.solvd.laba.people.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.function.Supplier;

public class Appointment implements Scheduleable {
    private static final Logger LOGGER = LogManager.getLogger(Appointment.class);
    private Supplier<Status> newAppointmentStatus = () -> Status.SCHEDULED;
    private LocalDate date;
    private LocalTime time;
    private Status status;

    public Appointment(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
        this.status = newAppointmentStatus.get();;
        LOGGER.info("Appointment scheduled for: " + date + " " + time);
    }

    public Appointment(LocalDate date, LocalTime time, Status status) {
        this.date = date;
        this.time = time;
        this.status = status;
        LOGGER.info("Appointment: " + date + " " + time + " " + status);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void schAppointment(LocalDate userDate, LocalTime userTime) throws IllegalArgumentException {
        if (userDate.isBefore(LocalDate.now())) {
            LOGGER.error("Appointment date cannot be in the past");
            throw new IllegalArgumentException("Appointment date cannot be in the past");
        }
        LOGGER.info("Appointment scheduled successfully");
        this.date = userDate;
        this.time = userTime;
        this.status = Status.SCHEDULED;

    }

    @Override
    public void scheduleAppointment(LocalDate userDate, LocalTime userTime) {
        schAppointment(userDate,userTime);
    }

    @Override
    public void cancelAppointment() throws IllegalStateException {
        if (this.date.isEqual(LocalDate.now()) || this.time.isBefore(LocalTime.now())) {
            LOGGER.error("Appointment has already started");
            throw new IllegalStateException("Appointment has already started");
        }
        if (this.status.equals("Completed")) {
            LOGGER.error("Appointment has already been completed");
            throw new IllegalStateException("Appointment has already been completed");
        }
        LOGGER.info("Canceling appointment");
        this.date = null;
        this.time = null;
        this.status = Status.CANCELLED;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "date=" + date +
                ", time=" + time +
                ", status='" + status + '\'' +
                '}';
    }
}
