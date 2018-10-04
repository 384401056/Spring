package com.blueice.weathercollection.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class WeatherDataCollectionServiceImpl implements WeatherDataCollectionService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;

    private final String WEATHER_API = "http://wthrcdn.etouch.cn/weather_mini"; //天气api接口
    //日志
    private final static Logger logger = LoggerFactory.getLogger(WeatherDataCollectionServiceImpl.class);

    @Override
    public void syncDataByCityId(String cityId) {
        String url = WEATHER_API + "?citykey=" + cityId;
        this.saveWeatherData(url);
        logger.info("同步 "+ cityId +" 天气完成");
    }

    /**
     * 保存数据到Redis数据库
     * @param url
     */
    private void saveWeatherData(String url) {
        String key = url;
        String value = null;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class); //用于发送http请求
        if (response.getStatusCodeValue() == 200) {
            value = response.getBody();
            redisTemplate.opsForValue().set(key, value, 1L, TimeUnit.HOURS); //1L redis缓存的超时时间.
        }
    }
}
