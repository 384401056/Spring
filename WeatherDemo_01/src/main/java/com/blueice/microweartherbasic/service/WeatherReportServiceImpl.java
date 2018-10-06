package com.blueice.microweartherbasic.service;

import com.blueice.microweartherbasic.bean.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    WeatherDataService weatherDataService;

    @Override
    public Weather getDataByCityId(String cityId) {
        //只返回weatherRespons中的data部分
        return weatherDataService.getDataByCityId(cityId).getData();
    }
}
