package com.blueice.weathercollection.feign;

import com.blueice.weathercollection.bean.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("zuul-server")
public interface DataClient {
    @GetMapping("/city/cities")
    List<City> getListCity() throws Exception;
}
