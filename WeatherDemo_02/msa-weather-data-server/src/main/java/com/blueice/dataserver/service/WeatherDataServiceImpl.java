package com.blueice.dataserver.service;

import com.blueice.dataserver.bean.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final int TIME_OUT = 1; //redis缓存的超时时间,1小时.
    private final String WEATHER_API = "http://wthrcdn.etouch.cn/weather_mini"; //天气api接口

    //日志
    private final static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String url = WEATHER_API + "?citykey=" + cityId;
        return doGetWeatherData(url);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String url = WEATHER_API + "?city=" + cityName;
        return doGetWeatherData(url);
    }


    private WeatherResponse doGetWeatherData(String url) {

        ObjectMapper mapper = new ObjectMapper(); //用于json解析
        WeatherResponse weatherResponse = null;
        String key = url;
        String strBody = null;

        //如果redis缓存中没有数据，就抛出异常
        if (!this.redisTemplate.hasKey(key)) {
            logger.error("key:" + key + "不存在redis中！");
            throw new RuntimeException("没有相应的天气数据!");
        } else {
            strBody = redisTemplate.opsForValue().get(key);
            logger.info("redis中有数据");
        }
        try {
            weatherResponse = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            logger.info("JSON序列化异常!");
            throw new RuntimeException("天气信息解析失败！");
        }
        return weatherResponse;
    }
}
