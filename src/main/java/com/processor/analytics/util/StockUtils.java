package com.processor.analytics.util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockUtils {

    public double getMean(List<Double> prices) {
        return prices.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
    }

    public Double getMedian(List<Double> prices) {
        if (prices.size() % 2 == 0) {
            return (prices.get(prices.size() / 2 - 1) + prices.get(prices.size() / 2)) / 2;
        } else {
            return prices.get(prices.size() / 2);
        }
    }
}
