package com.blueice.weathercollection.feign;

import com.blueice.weathercollection.bean.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("city-server")
public interface CityClient {
    @GetMapping("/cities")
    List<City> getListCity() throws Exception;
}
