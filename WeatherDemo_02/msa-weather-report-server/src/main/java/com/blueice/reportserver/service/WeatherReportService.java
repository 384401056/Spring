package com.blueice.reportserver.service;

import com.blueice.reportserver.bean.Weather;

/**
 * 应用API
 */
public interface WeatherReportService {
    /**
     * 根据城市ID获取天气信息
     *
     * @param cityId
     * @return
     */
    Weather getDataByCityId(String cityId);
}
