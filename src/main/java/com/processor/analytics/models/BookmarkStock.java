package com.processor.analytics.models;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "BookmarkStock")
public class BookmarkStock {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String stock;
    private String operator;
    private Integer amount;
}
