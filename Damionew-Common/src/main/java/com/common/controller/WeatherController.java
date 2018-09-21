/**
 * @author yinyunqi
 * @datetime 2018年9月21日
 * @Content 
 */
package com.common.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.feign.WeatherFeign;

import io.swagger.annotations.ApiOperation;

@Controller
public class WeatherController {
	
	@Autowired
	WeatherFeign weatherFeign;
	
	@ApiOperation(value = "获取天气信息",notes ="通过和风天气接口获取并处理天气信息")
	@ResponseBody
	@RequestMapping(value = "/getWeatherInfo",method = RequestMethod.GET)
	public String locationTransform(@RequestParam("lng") String lng,@RequestParam("lat") String lat) throws IOException {
		System.out.println("lng:"+lng+"lat:"+lat);
		return weatherFeign.locationTransform(lng,lat);
	}
}
