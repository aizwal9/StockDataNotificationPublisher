package com.processor.analytics.models;

import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "StockQuote")
public class TimeSeriesResponseStock {
    private String symbol;
    private String lastRefreshed;
    private List<StockUnit> stockUnits;
}
