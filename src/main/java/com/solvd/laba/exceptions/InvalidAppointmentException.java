package com.solvd.laba.exceptions;

public class InvalidAppointmentException extends Exception{
    public InvalidAppointmentException(String message) {
        super(message);
    }
    @Override
    public String getMessage() {
        return "Invalid date or time format. Please provide a valid date and time in the format YYYY-MM-DD HH:MM.";
    }
}
