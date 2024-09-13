package com.processor.analytics.repository;

import com.processor.analytics.models.IntraDayStockQuote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;


public interface IntraDayStockQuoteRepository extends MongoRepository<IntraDayStockQuote, String> {
    IntraDayStockQuote findBySymbol(@Param("symbol") String symbol);
}
