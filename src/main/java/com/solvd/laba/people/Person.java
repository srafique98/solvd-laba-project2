package com.solvd.laba.people;

import com.solvd.laba.billing.Cost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public abstract class Person {
    private static final Logger LOGGER = LogManager.getLogger(Person.class);
    protected String firstName;
    protected String lastName;

    public Person(String firstName, String lastName) {
        LOGGER.info("Person created: " + firstName + " " + lastName);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public abstract String getFullName();
    public abstract String getInfo();

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
