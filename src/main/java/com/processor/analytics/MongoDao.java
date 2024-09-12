package com.processor.analytics;

import com.mongodb.client.MongoDatabase;
import com.processor.analytics.models.TimeSeriesResponseStock;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoDao {

    private final MongoTemplate mongoTemplate;

    public MongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void storeStockQuote(MongoDatabase mongoDatabase, TimeSeriesResponseStock response, String collection) {
//        mongoDatabase.getCollection(collection, TimeSeriesResponseStock.class).insertOne(response);
        mongoTemplate.save(response, collection);
    }
}
