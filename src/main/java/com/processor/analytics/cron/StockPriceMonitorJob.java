package com.processor.analytics.cron;

import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.processor.analytics.models.BookmarkStock;
import com.processor.analytics.service.BookmarkStocksService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class StockPriceMonitorJob implements Job {

    @Resource
    private StockPriceMonitor stockPriceMonitor;
    @Resource
    private Publisher publisher;
    @Resource
    private BookmarkStocksService bookmarkStocksService;

    @Override
    public void execute(JobExecutionContext context) {
        List<BookmarkStock> bookmarkedStocks = bookmarkStocksService.getAll();
        for (BookmarkStock bookmarkStock : bookmarkedStocks) {
            if (stockPriceMonitor.monitorStock(bookmarkStock)) {
                sendNotification(bookmarkStock);
            }
        }
    }

    private void sendNotification(BookmarkStock bookmarkStock) {

        String message = getMessageFormat(bookmarkStock);
        log.info("Send notification");

        PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                .setData(ByteString.copyFromUtf8(message))
                .build();

        publisher.publish(pubsubMessage);
    }

    private String getMessageFormat(BookmarkStock bookmarkStock) {
        return switch (bookmarkStock.getOperator()) {
            case ">" ->
                    String.format("%s price is greater than %.2f", bookmarkStock.getStock(), bookmarkStock.getAmount());
            case "=" -> String.format("%s price is equal to %.2f", bookmarkStock.getStock(), bookmarkStock.getAmount());
            case "<" ->
                    String.format("%s price is less than %.2f", bookmarkStock.getStock(), bookmarkStock.getAmount());
            default -> "";
        };
    }
}
