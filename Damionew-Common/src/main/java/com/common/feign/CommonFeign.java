package com.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "damionew-common")
public interface CommonFeign {
	
	@GetMapping(value = "getUsername")
	public String getUsername();
}
