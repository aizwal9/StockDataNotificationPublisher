package com.processor.analytics.cron;

import com.processor.analytics.models.BookmarkStock;
import com.processor.analytics.models.IntraDayStockQuote;
import com.processor.analytics.repository.IntraDayStockQuoteRepository;
import com.processor.analytics.service.VantageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class StockPriceMonitor {

    @Resource
    private VantageService vantageService;

    @Resource
    private IntraDayStockQuoteRepository intraDayStockQuoteRepository;

    public boolean monitorStock(BookmarkStock bookmarkStock) {
        IntraDayStockQuote intraDayStockQuote = intraDayStockQuoteRepository.findBySymbol(bookmarkStock.getStock());
        if (isNull(intraDayStockQuote)) {
            intraDayStockQuote = vantageService.getIntradayStockData(bookmarkStock.getStock());
        }
        return switch (bookmarkStock.getOperator()) {
            case "<" -> intraDayStockQuote.getStockUnits().get(0).getClose() < bookmarkStock.getAmount();
            case ">" -> intraDayStockQuote.getStockUnits().get(0).getClose() > bookmarkStock.getAmount();
            case "=" -> intraDayStockQuote.getStockUnits().get(0).getClose() == bookmarkStock.getAmount();
            default -> false;
        };
    }

}
