package com.blueice.weathercollection.service;

public interface WeatherDataCollectionService {

    /**
     * 根据城市ID获取天气信息
     *
     * @param cityId
     */
    void syncDataByCityId(String cityId);
}
