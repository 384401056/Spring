package com.blueice.reportserver.controller;

import com.blueice.reportserver.bean.City;
import com.blueice.reportserver.service.WeatherReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用控制器
 */
@RestController
@RequestMapping("/report")
public class WeatherReportController {
    @Autowired
    WeatherReportService weatherReportService;

    private final static Logger logger = LoggerFactory.getLogger(WeatherReportController.class);

    @GetMapping("/cityId/{cityId}")
    public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model)throws Exception{

        List<City> cityList = null;
        try {
            cityList = new ArrayList<>();
            City city = new City();
            city.setCityId("101280601");
            city.setCityName("深圳");
            cityList.add(city);
        }catch (Exception ex){
            logger.error("获取城市信息失败！");
            throw new RuntimeException("获取城市信息失败！", ex);
        }
        model.addAttribute("title","我的天气预报");
        model.addAttribute("cityId", cityId);
        model.addAttribute("report", weatherReportService.getDataByCityId(cityId));
        model.addAttribute("cityList", cityList);
        return new ModelAndView("report","reportModel",model);
    }
}
