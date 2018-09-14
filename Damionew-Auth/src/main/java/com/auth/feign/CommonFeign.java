package com.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 调用Common公共模块中的接口
 * @author yinyunqi
 *
 */
@FeignClient("damionew-common")
public interface CommonFeign {
	
	@GetMapping("/transactional")
	public void transactionalTest();
}
