package com.processor.analytics.repository;

import com.processor.analytics.models.BookmarkStock;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookmarkRepository extends MongoRepository<BookmarkStock, String> {
}
