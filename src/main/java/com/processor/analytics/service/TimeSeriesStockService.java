package com.processor.analytics.service;

import com.processor.analytics.models.TimeSeriesResponseStock;
import com.processor.analytics.repository.TimeSeriesResponseStockRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeSeriesStockService {

    @Resource
    private TimeSeriesResponseStockRepository timeSeriesResponseStockRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public TimeSeriesResponseStock findOne(String symbol){
//        return Optional.of(timeSeriesResponseStockRepository.findBySymbol(symbol)).get();
        return mongoTemplate.find(Query.query(Criteria.where("symbol").is(symbol)), TimeSeriesResponseStock.class).get(0);
    }

    public List<TimeSeriesResponseStock> findAll(){
        return timeSeriesResponseStockRepository.findAll();
    }

    public TimeSeriesResponseStock save(TimeSeriesResponseStock stock){
        return timeSeriesResponseStockRepository.save(stock);
    }
}
