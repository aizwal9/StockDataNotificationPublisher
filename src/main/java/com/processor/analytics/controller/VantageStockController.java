package com.processor.analytics.controller;

import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import com.processor.analytics.VantageService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
public class VantageStockController {

    @Value("${alpha.vantage.api.key}")
    private String apiKey;
    @Value("${spring.data.mongodb.uri}")
    private String connectionString;
    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Resource
    private VantageService vantageService;

    @GetMapping("/list")
    public List<TimeSeriesResponse> getStockData(@RequestBody List<String> symbols) {
        return vantageService.getSingleStockData(symbols,apiKey,connectionString,databaseName);
    }

}
