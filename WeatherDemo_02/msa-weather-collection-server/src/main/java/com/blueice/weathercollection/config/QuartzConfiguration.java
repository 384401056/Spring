package com.blueice.weathercollection.config;

import com.blueice.weathercollection.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Quartz 配置类
 */
@Configuration
public class QuartzConfiguration {

    /**
     * 定义了一个特定的Job,名为:weatherDataSyncJob
     *
     * @return
     */
    @Bean
    public JobDetail weatherDataSyncJobJobDetail() {
        return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("weatherDataSyncJob").storeDurably().build();
    }

    /**
     * 定义了一个触发器，来定时触发Job.
     *
     * @return
     */
    @Bean
    public Trigger sampleJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInMinutes(30).repeatForever(); //每*分钟重复一次

        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobJobDetail()).withIdentity("weatherDataSyncTrigger")
                .withSchedule(scheduleBuilder).build(); //每*分钟执行一次Job
    }
}
