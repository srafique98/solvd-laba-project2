package com.solvd.laba.enums;

import java.util.Arrays;

public enum Country {
    USA(1, "United States of America", CurrencyType.USD),
    ITALY(2, "Italy", CurrencyType.EUR);

    private final int countryId;
    private final String countryName;
    private final CurrencyType currency;

    Country(int countryId, String countryName, CurrencyType currency) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.currency = currency;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public static Country getCountryById(int countryId) {
        return Arrays.stream(Country.values())
                .filter(country -> country.getCountryId() == countryId)
                .findFirst()
                .orElse(null);
    }

    public static Country getCountryByName(String countryName) {
        return Arrays.stream(Country.values())
                .filter(country -> country.getCountryName().equalsIgnoreCase(countryName))
                .findFirst()
                .orElse(null);
    }
}
