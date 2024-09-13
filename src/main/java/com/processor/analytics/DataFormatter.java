package com.processor.analytics;

import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import com.processor.analytics.models.CustomStockUnit;
import com.processor.analytics.models.IntraDayStockQuote;
import com.processor.analytics.models.TimeSeriesResponseStock;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataFormatter {

    public TimeSeriesResponseStock formatTimeSeriesResponse(TimeSeriesResponse response){
        return TimeSeriesResponseStock.builder()
                .lastRefreshed(response.getMetaData().getLastRefreshed())
                .symbol(response.getMetaData().getSymbol())
                .stockUnits(getCustomStockUnits(response.getStockUnits()))
                .build();
    }

    public IntraDayStockQuote formatIntradayTimeSeriesResponse(TimeSeriesResponse response){
        return IntraDayStockQuote.builder()
                .lastRefreshed(response.getMetaData().getLastRefreshed())
                .symbol(response.getMetaData().getSymbol())
                .stockUnits(getCustomStockUnits(response.getStockUnits()))
                .build();
    }

    public List<CustomStockUnit> getCustomStockUnits(List<StockUnit> stockUnits){
        return stockUnits.parallelStream()
                .map(stockUnit -> CustomStockUnit.builder()
                        .low(stockUnit.getLow())
                        .high(stockUnit.getHigh())
                        .open(stockUnit.getOpen())
                        .close(stockUnit.getClose())
                        .adjustedClose(stockUnit.getAdjustedClose())
                        .volume(stockUnit.getVolume())
                        .dividendAmount(stockUnit.getDividendAmount())
                        .splitCoefficient(stockUnit.getSplitCoefficient())
                        .dateTime(stockUnit.getDate())
                        .build())
                .toList();
    }

}
