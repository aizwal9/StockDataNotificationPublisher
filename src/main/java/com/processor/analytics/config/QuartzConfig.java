package com.processor.analytics.config;

import com.processor.analytics.cron.StockPriceMonitorJob;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SimpleTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import java.util.Objects;

@Configuration
@Slf4j
public class QuartzConfig {

    @Resource
    private AutowiringSpringBeanJobFactory jobFactory;

    @Bean
    public JobDetailFactoryBean stockPriceMonitorJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(StockPriceMonitorJob.class);
        factoryBean.setDurability(true);
        return factoryBean;
    }

    @Bean
    public SimpleTriggerFactoryBean stockPriceMonitorTrigger() {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(Objects.requireNonNull(stockPriceMonitorJobDetail().getObject()));
        factoryBean.setStartDelay(0);
        factoryBean.setRepeatInterval(3600000);
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return factoryBean;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setJobFactory(jobFactory);
        factoryBean.setTriggers(stockPriceMonitorTrigger().getObject());
        return factoryBean;
    }
}
