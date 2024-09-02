package com.processor.analytics;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import com.mongodb.client.MongoDatabase;
import com.processor.analytics.util.StockUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    private StockUtils stockUtils;

    private static final int TIMEOUT = 100;


    public List<TimeSeriesResponse> getSingleStockData(List<String> symbols, String apiKey, String connectionString, String databaseName) {
        MongoDatabase mongoDatabase = MongoClientInitlizer.getInstance(connectionString).getDatabase(databaseName);
        List<TimeSeriesResponse> timeSeriesResponses = new ArrayList<>();
        Config config = Config.builder()
                .key(apiKey)
                .timeOut(TIMEOUT)
                .build();

        AlphaVantage.api().init(config);

        symbols.forEach(symbol -> {
            TimeSeriesResponse response = AlphaVantage.api()
                    .timeSeries()
                    .daily()
                    .forSymbol(symbol)
                    .outputSize(OutputSize.FULL)
                    .dataType(DataType.JSON)
                    .fetchSync();
            mongoDao.storeStockQuote(mongoDatabase, dataFormatter.formatTimeSeriesResponse(response), STOCK_QUOTE.value);
            timeSeriesResponses.add(response);
        });

        return timeSeriesResponses;
    }

    public void monitorStocks(String symbol, String connectionString, String databaseName) {
        MongoDatabase mongoDatabase = MongoClientInitlizer.getInstance(connectionString).getDatabase(databaseName);
        Document query = new Document("symbol", symbol);
        List<StockUnit> stockUnits = mongoDatabase.getCollection(STOCK_QUOTE.value).find(query).first().get(STOCK_UNITS.value, List.class);
        log.info(stockUnits.toString());
    }

}
