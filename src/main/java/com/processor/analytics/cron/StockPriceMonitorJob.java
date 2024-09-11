package com.processor.analytics.cron;

import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.processor.analytics.VantageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class StockPriceMonitorJob implements Job {
    
    @Resource private VantageService vantageService;
    @Resource private Publisher publisher;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException{
        List<String> bookmarkedStock = List.of("AAPL", "MSFT", "GOOGL");
        for (String stockSymbol : bookmarkedStock){
            sendNotification(stockSymbol,100, 90);
//            vantageService.monitorStocks(stockSymbol);
        }
    }

    private void sendNotification(String stockSymbol,double currentPrice,double yesterdayPrice){
        String message = String.format(
                "Stock alert: %s price dropped by %.2f%% (current : %.2f, yesterday %.2f)",
                stockSymbol,
                (yesterdayPrice-currentPrice)/yesterdayPrice * 100,
                currentPrice,
                yesterdayPrice
        );
        log.info("Send notification");

        PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                .setData(ByteString.copyFromUtf8(message))
                .build();

        publisher.publish(pubsubMessage);
    }
}
