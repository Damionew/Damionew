package com.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "eureka-server")

public interface ServiceHi {

	@RequestMapping(value = "/hi",method = RequestMethod.GET)
	public String sayHi();
}
