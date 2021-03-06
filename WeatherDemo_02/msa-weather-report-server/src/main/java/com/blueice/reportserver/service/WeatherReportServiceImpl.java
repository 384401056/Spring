package com.blueice.reportserver.service;

import com.blueice.reportserver.bean.Weather;
import com.blueice.reportserver.bean.WeatherResponse;
import com.blueice.reportserver.controller.WeatherReportController;
import com.blueice.reportserver.feign.CityClient;
import com.blueice.reportserver.feign.DataClient;
import com.blueice.reportserver.feign.WeatherDataClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    RestTemplate restTemplate;

//    @Autowired
//    WeatherDataClient weatherDataClient; //使用feign消费服务

    @Autowired
    DataClient dataClient;

    private final static Logger logger = LoggerFactory.getLogger(WeatherReportServiceImpl.class);

    @Override
    public Weather getDataByCityId(String cityId) {

        return dataClient.getDataByCityId(cityId).getData(); //使用feign+zuul消费服务

//        return weatherDataClient.getDataByCityId(cityId).getData(); //使用feign消费服务

//        String url = "http://localhost:9090/weather/cityId/" + cityId;
//        ObjectMapper mapper = new ObjectMapper(); //用于json解析
//        WeatherResponse weatherResponse = null;
//        String strBody = null;
//
//        try {
//            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//            if (response.getStatusCodeValue() == 200) {
//                strBody = response.getBody();
//            } else {
//                logger.info("网络获取数据失败！");
//            }
//            weatherResponse = mapper.readValue(strBody, WeatherResponse.class);
//        } catch (IOException ex) {
//            logger.error("JSON序列化异常!", ex);
//            ex.printStackTrace();
//        }
//
//        return weatherResponse.getData();
    }
}
