package com.processor.analytics.service;

import com.processor.analytics.models.IntraDayStockQuote;
import com.processor.analytics.models.TimeSeriesResponseStock;
import com.processor.analytics.repository.TimeSeriesResponseStockRepository;
import com.processor.analytics.repository.IntraDayStockQuoteRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeSeriesStockService {

    @Resource
    private TimeSeriesResponseStockRepository dailyTimeSeriesResponseStockRepository;
    @Resource
    private IntraDayStockQuoteRepository intradayTimeSeriesResponseStockRepository;

    public TimeSeriesResponseStock findOneDaily(String symbol) {
        return dailyTimeSeriesResponseStockRepository.findBySymbol(symbol);
    }

    public IntraDayStockQuote findOneIntra(String symbol) {
        return intradayTimeSeriesResponseStockRepository.findBySymbol(symbol);
    }


    public List<TimeSeriesResponseStock> findAll() {
        return dailyTimeSeriesResponseStockRepository.findAll();
    }

    public TimeSeriesResponseStock saveDaily(TimeSeriesResponseStock stock) {
        return dailyTimeSeriesResponseStockRepository.save(stock);
    }

    public IntraDayStockQuote saveIntra(IntraDayStockQuote stock) {
        return intradayTimeSeriesResponseStockRepository.save(stock);
    }
}
