package com.solvd.laba.enums;

public enum CurrencyType {
    USD("US Dollar", "$"),
    EUR("Euro", "€");
    private final String name;
    private final String symbol;

    CurrencyType(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public static CurrencyType getByOption(int option) {
        switch (option) {
            case 1:
                return USD;

            case 2:
                return EUR;

            default:
                throw new IllegalArgumentException("Invalid currency: " + option);
        }
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }
}
