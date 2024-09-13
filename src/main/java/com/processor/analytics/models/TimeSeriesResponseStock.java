package com.processor.analytics.models;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "DailyStockQuote")
public class TimeSeriesResponseStock {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String symbol;
    private String lastRefreshed;

    private List<CustomStockUnit> stockUnits;
}
