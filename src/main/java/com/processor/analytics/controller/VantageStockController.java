package com.processor.analytics.controller;

import com.processor.analytics.models.TimeSeriesResponseStock;
import com.processor.analytics.service.VantageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vantage")
public class VantageStockController {

    @Resource
    private VantageService vantageService;

    @GetMapping("/{symbol}")
    public TimeSeriesResponseStock getStockData(@PathVariable(name = "symbol") String symbol) {
        return vantageService.getSingleStockData(symbol);
    }

}
