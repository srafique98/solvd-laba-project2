package com.solvd.laba.interfaces;

import java.time.LocalDate;
import java.time.LocalTime;

public interface Scheduleable {
    void scheduleAppointment(LocalDate userDate, LocalTime userTime);
    void cancelAppointment();

}
