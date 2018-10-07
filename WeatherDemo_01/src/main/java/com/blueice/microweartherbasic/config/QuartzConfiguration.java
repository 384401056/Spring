package com.blueice.microweartherbasic.config;

import com.blueice.microweartherbasic.bean.Weather;
import com.blueice.microweartherbasic.job.WeatherDataSyncJob;
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
                .withIntervalInMinutes(5).repeatForever(); //每5分钟重复一次

        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobJobDetail()).withIdentity("weatherDataSyncTrigger")
                .withSchedule(scheduleBuilder).build(); //每5分钟执行一次Job
    }
}
