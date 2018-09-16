package com.common.feign;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "damionew-weather")
public interface WeatherFeign {
	
	@GetMapping(value = "/weatherGetWeatherInfo")
	public String locationTransform(HttpServletRequest request);
}
