package com.blueice.microweartherbasic.service;

import com.blueice.microweartherbasic.bean.WeatherResponse;

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

    /**
     * 根据城市ID同步天气数据
     * @param cityId
     * @return
     */
    void syncDataByCityId(String cityId);


    /**
     * 根据城市名称同步天气数据
     * @param cityName
     */
    void syncDataByCityName(String cityName);
}
