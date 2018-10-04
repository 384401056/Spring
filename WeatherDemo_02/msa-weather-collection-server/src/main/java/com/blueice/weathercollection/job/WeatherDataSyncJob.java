package com.blueice.weathercollection.job;

import com.blueice.weathercollection.bean.City;
import com.blueice.weathercollection.service.WeatherDataCollectionService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 天气数据定时同步类
 */
public class WeatherDataSyncJob extends QuartzJobBean {

    //日志
    private final static Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Autowired
    private WeatherDataCollectionService service;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        //根据Quartz的配置，会重复执行此处的代码
        List<City> cityList = null;
        try {
            cityList = new ArrayList<>();
            City city = new City();
            city.setCityId("101280601");
            cityList.add(city);

            for(City ct:cityList){
                String cityId = ct.getCityId();
                service.syncDataByCityId(cityId);
            }

//            cityList = cityDataService.listCity();
//            for(City city:cityList){
////                weatherDataService.syncDataByCityId(city.getCityId());
//                weatherDataService.syncDataByCityName(city.getCityName());
//            }
        }catch (Exception ex){
            logger.error("获取城市信息异常！", ex);
            throw new RuntimeException("获取城市信息异常!", ex);
        }

        logger.info("天气数据同步成功！");
    }
}
