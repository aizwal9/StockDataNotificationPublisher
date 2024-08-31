package com.processor.analytics;

import com.mongodb.client.MongoDatabase;
import lombok.Data;

@Data
public class MongoDbClient {

    private MongoDbClient mongoClient;
    private MongoDatabase mongoDatabase;


    public MongoDbClient(MongoDbClient mongoDbClient, MongoDatabase mongoDatabase) {
        this.mongoClient = mongoDbClient;
        this.mongoDatabase = mongoDatabase;
    }

    public void close(){
        mongoClient.close();
    }

}
