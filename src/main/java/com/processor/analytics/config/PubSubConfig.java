package com.processor.analytics.config;

import com.google.cloud.pubsub.v1.Publisher;
import com.google.pubsub.v1.TopicName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class PubSubConfig {

    @Value("${spring.cloud.gcp.pubsub.project-id}")
    private String projectId;
    @Value("${spring.cloud.gcp.pubsub.topic-id}")
    private String topicId;

    @Bean
    public Publisher publisher() throws IOException {
        TopicName topicName = TopicName.of(projectId, topicId);
        return Publisher.newBuilder(topicName).build();
    }
}
