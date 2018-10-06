package com.blueice.microweartherbasic.service;

import com.blueice.microweartherbasic.bean.City;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CityDataService {
    List<City> listCity() throws Exception;
}
