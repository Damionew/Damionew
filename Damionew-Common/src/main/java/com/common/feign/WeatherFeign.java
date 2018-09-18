package com.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "damionew-weather")
public interface WeatherFeign {
	
	@RequestMapping(value = "/weatherGetWeatherInfo",method=RequestMethod.GET)
	public String locationTransform(@RequestParam("lng") String lng,@RequestParam("lat") String lat);
}
