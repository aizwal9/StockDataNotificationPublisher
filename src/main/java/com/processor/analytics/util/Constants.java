package com.processor.analytics.util;

public enum Constants {
    STOCK_QUOTE("StockQuote"),
    STOCK_UNITS("stockUnits");

    public final String value;

    Constants(String value) {
        this.value = value;
    }
}
