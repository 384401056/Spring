package com.blueice.msaweathercityserver.service;


import com.blueice.msaweathercityserver.bean.City;

import java.util.List;

public interface CityDataService {
    List<City> listCity() throws Exception;
}
