package com.blueice.microweartherbasic.controller;

import com.blueice.microweartherbasic.service.CityDataService;
import com.blueice.microweartherbasic.service.WeatherReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 应用控制器
 */
@RestController
@RequestMapping("/report")
public class WeatherReportController {
    @Autowired
    WeatherReportService weatherReportService;

    @Autowired
    CityDataService cityDataService;

    @GetMapping("/cityId/{cityId}")
    public ModelAndView getReportByCityId(
            @PathVariable("cityId") String cityId, Model model
    )throws Exception{
        model.addAttribute("title","我的天气预报");
        model.addAttribute("cityId", cityId);
        model.addAttribute("cityList",cityDataService.listCity());
        model.addAttribute("report", weatherReportService.getDataByCityId(cityId));
        return new ModelAndView("report","reportModel",model);
    }
}
