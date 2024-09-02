package com.processor.analytics;

import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import com.processor.analytics.models.TimeSeriesResponseStock;
import org.springframework.stereotype.Component;

@Component
public class DataFormatter {

    public TimeSeriesResponseStock formatTimeSeriesResponse(TimeSeriesResponse response){
        return TimeSeriesResponseStock.builder()
                .lastRefreshed(response.getMetaData().getLastRefreshed())
                .symbol(response.getMetaData().getSymbol())
                .stockUnits(response.getStockUnits())
                .build();
    }

}
