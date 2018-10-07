package com.blueice.reportserver.feign;

import com.blueice.reportserver.bean.City;
import com.blueice.reportserver.bean.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("zuul-server")
public interface DataClient {

    @GetMapping("/city/cities")
    List<City> getListCity() throws Exception;

    @GetMapping("/data/weather/cityId/{cityId}")
    WeatherResponse getDataByCityId(@PathVariable("cityId") String cityId);

}
