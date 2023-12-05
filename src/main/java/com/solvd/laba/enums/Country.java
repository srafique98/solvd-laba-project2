package com.solvd.laba.enums;

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
        for (Country country : Country.values()) {
            if (country.getCountryId() == countryId) {
                return country;
            }
        }
        return null;
    }

    public static Country getCountryByName(String countryName) {
        for (Country country : Country.values()) {
            if (country.getCountryName().equalsIgnoreCase(countryName)) {
                return country;
            }
        }
        return null;
    }
}
