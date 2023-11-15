package com.solvd.laba.exceptions;

public class AppointmentConflictException extends Exception {
    public AppointmentConflictException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Customer already has an appointment at that time. Please choose a different date or time.";
    }
}
