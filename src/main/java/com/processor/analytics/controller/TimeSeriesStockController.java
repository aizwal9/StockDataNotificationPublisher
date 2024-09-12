package com.processor.analytics.controller;

import com.processor.analytics.models.TimeSeriesResponseStock;
import com.processor.analytics.service.TimeSeriesStockService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stock")
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class TimeSeriesStockController {

    @Resource
    private TimeSeriesStockService timeSeriesStockService;

    @GetMapping("/{symbol}")
    public TimeSeriesResponseStock getStockData(@PathVariable(name = "symbol") String symbol) {
        return timeSeriesStockService.findOne(symbol);
    }

}
