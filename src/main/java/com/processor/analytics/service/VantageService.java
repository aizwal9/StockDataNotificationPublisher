package com.processor.analytics.service;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import com.processor.analytics.DataFormatter;
import com.processor.analytics.models.IntraDayStockQuote;
import com.processor.analytics.models.TimeSeriesResponseStock;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.crazzyghost.alphavantage.parameters.Interval.SIXTY_MIN;

@Component
@Slf4j
public class VantageService {

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

}
