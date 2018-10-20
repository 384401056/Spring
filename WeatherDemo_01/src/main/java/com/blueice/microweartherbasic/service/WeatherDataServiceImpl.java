package com.blueice.microweartherbasic.service;

import com.blueice.microweartherbasic.bean.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
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
    private RestTemplate restTemplate;

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

    @Override
    public void syncDataByCityId(String cityId) {
        String url = WEATHER_API + "?citykey=" + cityId;
        this.saveWeatherData(url);
    }

    @Override
    public void syncDataByCityName(String cityName) {
        String url = WEATHER_API + "?city=" + cityName;
        this.saveWeatherData(url);
    }

    /**
     * 保存数据到Redis数据库
     *
     * @param url
     */
    private void saveWeatherData(String url) {
        String key = url;
        String value = null;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class); //用于发送http请求
        if (response.getStatusCodeValue() == 200) {
            value = response.getBody();
            redisTemplate.opsForValue().set(key, value, TIME_OUT, TimeUnit.HOURS);
        }
    }


    private WeatherResponse doGetWeatherData(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class); //用于发送http请求
        ObjectMapper mapper = new ObjectMapper(); //用于json解析
        WeatherResponse weatherResponse = null;
        String strBody = null;

        //如果redis缓存中没有数据，则从网络获取
        if (!this.redisTemplate.hasKey(url)) {
            if (response.getStatusCodeValue() == 200) {
                strBody = response.getBody();
                //从网络获取数据后，存入redis中,url是key,strBody就是value,并设置超时时间
                redisTemplate.opsForValue().set(url, strBody, TIME_OUT, TimeUnit.HOURS);
                logger.info("没有数据，从网络获取数据");
            }
        } else {
            strBody = redisTemplate.opsForValue().get(url);
            logger.info("redis中有数据");
        }
        try {
            weatherResponse = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            logger.info("JSON序列化异常!");
            e.printStackTrace();
        }

        return weatherResponse;
    }
}
