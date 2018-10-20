package com.blueice.weathercollection.job;

import com.blueice.weathercollection.bean.City;
import com.blueice.weathercollection.feign.CityClient;
import com.blueice.weathercollection.feign.DataClient;
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

//    @Autowired
//    private CityClient cityClient; //使用feign获取数据
    @Autowired
    DataClient dataClient;

    @Autowired
    private WeatherDataCollectionService service;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        //根据Quartz的配置，会重复执行此处的代码
        List<City> cityList = null;
        try {

            cityList = dataClient.getListCity(); //使用feign+zuul获取数据
//            cityList = cityClient.getListCity(); //使用feign获取数据

            for (City ct : cityList) {
                String cityId = ct.getCityId();
                service.syncDataByCityId(cityId);
            }

        } catch (Exception ex) {
            logger.error("获取城市信息异常！", ex);
            throw new RuntimeException("获取城市信息异常!", ex);
        }

        logger.info("天气数据同步成功！");
    }
}
