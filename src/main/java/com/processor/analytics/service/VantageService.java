package com.processor.analytics.service;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import com.mongodb.client.MongoDatabase;
import com.processor.analytics.DataFormatter;
import com.processor.analytics.MongoClientInitlizer;
import com.processor.analytics.MongoDao;
import com.processor.analytics.models.IntraDayStockQuote;
import com.processor.analytics.models.TimeSeriesResponseStock;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.crazzyghost.alphavantage.parameters.Interval.SIXTY_MIN;
import static com.processor.analytics.util.Constants.STOCK_QUOTE;
import static com.processor.analytics.util.Constants.STOCK_UNITS;

@Component
@Slf4j
public class VantageService {

    @Resource
    private MongoDao mongoDao;
    @Resource
    private DataFormatter dataFormatter;
    @Resource
    private TimeSeriesStockService timeSeriesStockService;

    @Value("${alpha.vantage.api.key}")
    private String apiKey;
    private static final int TIMEOUT = 1000;


    public IntraDayStockQuote getIntradayStockData(String symbol) {
        Config config = Config.builder()
                .key(apiKey)
                .timeOut(TIMEOUT)
                .build();

        AlphaVantage.api().init(config);

        TimeSeriesResponse response = AlphaVantage.api()
                .timeSeries()
                .intraday()
                .interval(SIXTY_MIN)
                .forSymbol(symbol)
                .outputSize(OutputSize.FULL)
                .dataType(DataType.JSON)
                .fetchSync();
        return timeSeriesStockService.saveIntra(dataFormatter.formatIntradayTimeSeriesResponse(response));
    }

    public TimeSeriesResponseStock getDailyStockData(String symbol) {
        Config config = Config.builder()
                .key(apiKey)
                .timeOut(TIMEOUT)
                .build();

        AlphaVantage.api().init(config);

        TimeSeriesResponse response = AlphaVantage.api()
                .timeSeries()
                .daily()
                .forSymbol(symbol)
                .outputSize(OutputSize.FULL)
                .dataType(DataType.JSON)
                .fetchSync();
        return timeSeriesStockService.saveDaily(dataFormatter.formatTimeSeriesResponse(response));
    }


    public void monitorStocks(String symbol, String connectionString, String databaseName) {
        MongoDatabase mongoDatabase = MongoClientInitlizer.getInstance(connectionString).getDatabase(databaseName);
        Document query = new Document("symbol", symbol);
        List<StockUnit> stockUnits = mongoDatabase.getCollection(STOCK_QUOTE.value).find(query).first().get(STOCK_UNITS.value, List.class);
        log.info(stockUnits.toString());
    }

}
