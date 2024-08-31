package com.processor.analytics;

import io.github.mainstringargs.alphavantagescraper.output.quote.StockQuotesResponse;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
public class StockDataController {

    @Value("${alpha.vantage.api.key}")
    private String apiKey;
    @Value("${spring.data.mongodb.uri}")
    private String connectionString;
    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Resource
    private VantageService vantageService;

    @GetMapping("/list")
    public List<StockQuotesResponse> getStockData(@RequestBody List<String> symbols) {
        return vantageService.getSingleStockData(symbols,apiKey,connectionString,databaseName);
    }
}
