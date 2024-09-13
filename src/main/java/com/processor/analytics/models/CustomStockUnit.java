package com.processor.analytics.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomStockUnit {
    private double open;
    private double high;
    private double low;
    private double close;
    private double adjustedClose;
    private long volume;
    private double dividendAmount;
    private double splitCoefficient;
    private String dateTime;
}
