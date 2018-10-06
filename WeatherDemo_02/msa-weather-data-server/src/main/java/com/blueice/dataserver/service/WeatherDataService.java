package com.blueice.dataserver.service;


import com.blueice.dataserver.bean.WeatherResponse;

public interface WeatherDataService {
    /**
     * 根据城市ID获取天气信息
     * @param cityId 城市ID
     * @return
     */
    WeatherResponse getDataByCityId(String cityId);

    /**
     * 根据城市名称获取天气信息
     * @param cityName 城市名
     * @return
     */
    WeatherResponse getDataByCityName(String cityName);

}
