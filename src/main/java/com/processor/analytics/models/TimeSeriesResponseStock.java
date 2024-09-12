package com.processor.analytics.models;

import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Builder
@Document(collection = "StockQuote")
public class TimeSeriesResponseStock {
    @Id
    private String id;
    @Indexed(unique = true)
    @Field("symbol")
    private String symbol;
    private String lastRefreshed;
    private List<StockUnit> stockUnits;

}
