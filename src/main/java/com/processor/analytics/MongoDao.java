package com.processor.analytics;

import com.mongodb.client.MongoDatabase;
import io.github.mainstringargs.alphavantagescraper.output.quote.data.StockQuote;
import org.springframework.stereotype.Component;

@Component
public class MongoDao {

    public void storeStockQuote(MongoDatabase mongoDatabase, StockQuote response, String collection) {
        mongoDatabase.getCollection(collection, StockQuote.class).insertOne(response);
    }
}
