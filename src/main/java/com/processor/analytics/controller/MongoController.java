package com.processor.analytics.controller;

import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.processor.analytics.util.StockUtils;
import jakarta.annotation.Resource;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.processor.analytics.util.Constants.STOCK_QUOTE;
import static com.processor.analytics.util.Constants.STOCK_UNITS;

@RestController
@RequestMapping("/api/v1/stock")
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class MongoController {

    @Resource private MongoClient mongoClient;
    @Resource private StockUtils stockUtils;

    @Value("${spring.data.mongodb.uri}")
    private String connectionString;
    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @GetMapping("/{symbol}")
    public List<StockUnit> getStockData(@PathVariable(name = "symbol") String symbol){
        MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
        Document query = new Document("symbol", symbol);
        return mongoDatabase.getCollection(STOCK_QUOTE.value).find(query).first().get(STOCK_UNITS.value, List.class);
    }

}
