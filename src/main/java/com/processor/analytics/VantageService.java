package com.processor.analytics;

import com.mongodb.client.MongoDatabase;
import io.github.mainstringargs.alphavantagescraper.AlphaVantageConnector;
import io.github.mainstringargs.alphavantagescraper.StockQuotes;
import io.github.mainstringargs.alphavantagescraper.output.AlphaVantageException;
import io.github.mainstringargs.alphavantagescraper.output.quote.StockQuotesResponse;
import io.github.mainstringargs.alphavantagescraper.output.quote.data.StockQuote;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class VantageService {

    @Resource
    private MongoDao mongoDao;
    private static final String STOCK_QUOTE = "StockQuote";


    public List<StockQuotesResponse> getSingleStockData(List<String> symbols, String apiKey, String connectionString, String databaseName) {
        int timeout = 3000;
        AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
        StockQuotes stockQuotes = new StockQuotes(apiConnector);
        MongoDatabase mongoDatabase = MongoClientInitlizer.getInstance(connectionString).getDatabase(databaseName);
        List<StockQuotesResponse> responseList = new ArrayList<>();
        try {
            log.info("Stock: {}", symbols);
            symbols.forEach(symbol -> {
                StockQuotesResponse response = stockQuotes.quote(symbol);
                StockQuote stock = response.getStockQuote();
                mongoDao.storeStockQuote(mongoDatabase, stock, STOCK_QUOTE);
                log.info("Date: {} Price: {}", stock.getLatestTradingDay(), stock.getPrice());
                responseList.add(response);
            });
            return responseList;
        } catch (AlphaVantageException e) {
            log.error("something went wrong");
            return Collections.emptyList();
        }
    }

}
