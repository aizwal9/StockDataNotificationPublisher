package com.processor.analytics;

import com.mongodb.client.MongoDatabase;
import com.processor.analytics.models.TimeSeriesResponseStock;
import org.springframework.stereotype.Component;

@Component
public class MongoDao {

    public void storeStockQuote(MongoDatabase mongoDatabase, TimeSeriesResponseStock response, String collection) {
        mongoDatabase.getCollection(collection, TimeSeriesResponseStock.class).insertOne(response);
    }
}
