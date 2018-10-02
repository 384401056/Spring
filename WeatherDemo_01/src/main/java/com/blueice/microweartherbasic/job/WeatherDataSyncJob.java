package com.blueice.microweartherbasic.job;

import com.blueice.microweartherbasic.bean.City;
import com.blueice.microweartherbasic.service.CityDataService;
import com.blueice.microweartherbasic.service.WeatherDataService;
import com.blueice.microweartherbasic.service.WeatherDataServiceImpl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * 天气数据定时同步类
 */
public class WeatherDataSyncJob extends QuartzJobBean {

    //日志
    private final static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);

    @Autowired
    private WeatherDataService weatherDataService;
    @Autowired
    private CityDataService cityDataService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        //根据Quartz的配置，会重复执行此处的代码
        List<City> cityList = null;
        try {
            cityList = cityDataService.listCity();
            for(City city:cityList){
//                weatherDataService.syncDataByCityId(city.getCityId());
                weatherDataService.syncDataByCityName(city.getCityName());
            }
        }catch (Exception ex){
            logger.info("获取城市信息异常！");
        }

        logger.info("天气数据同步成功！");
    }
}
