package com.processor.analytics.repository;

import com.processor.analytics.models.TimeSeriesResponseStock;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("dailyTimeSeriesResponseStockRepository")
@Document(collection = "DailyStockQuote")
public interface TimeSeriesResponseStockRepository extends MongoRepository<TimeSeriesResponseStock, String> {
    TimeSeriesResponseStock findBySymbol(@Param("symbol") String symbol);
}
