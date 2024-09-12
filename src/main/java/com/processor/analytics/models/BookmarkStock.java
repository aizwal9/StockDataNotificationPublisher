package com.processor.analytics.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "BookmarkStock")
public class BookmarkStock {
    @Indexed(unique = true)
    private String stock;
}
