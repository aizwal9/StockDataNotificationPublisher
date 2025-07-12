package com.processor.analytics.controller;

import com.processor.analytics.models.IntraDayStockQuote;
import com.processor.analytics.models.TimeSeriesResponseStock;
import com.processor.analytics.service.TimeSeriesStockService;
import com.processor.analytics.service.VantageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/stock")
@CrossOrigin(origins = "http://localhost:5173")
public class TimeSeriesStockController {

    @Resource
    private TimeSeriesStockService timeSeriesStockService;
    @Resource
    private VantageService vantageService;

    @GetMapping("/daily/{symbol}")
    public TimeSeriesResponseStock getStockData(@PathVariable(name = "symbol") String symbol) {
        TimeSeriesResponseStock response = timeSeriesStockService.findOneDaily(symbol);
        if (response != null && response.getLastRefreshed() != null &&
               LocalDate.parse(response.getLastRefreshed()).isAfter(LocalDate.now().minusWeeks(1))) {
            return response;
        } else {
            return vantageService.getDailyStockData(symbol);
        }
    }

    @GetMapping("/intra/{symbol}")
    public IntraDayStockQuote getIntradayStockData(@PathVariable(name = "symbol") String symbol) {
        return Optional.ofNullable(timeSeriesStockService.findOneIntra(symbol)).orElseGet(() -> vantageService.getIntradayStockData(symbol));
    }

}
