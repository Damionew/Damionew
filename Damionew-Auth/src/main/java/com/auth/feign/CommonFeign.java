package com.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 调用Common公共模块中的接口
 * @author yinyunqi
 *
 */
@Api(value = "调用Common模块接口")
@FeignClient(value = "damionew-common")
public interface CommonFeign {
	
	@RequestMapping(value = "/hia",method = RequestMethod.GET)
	public String sayHia();
	
	@ApiOperation(value = "调用Common模块，跳转到Index页面",notes = "登录成功后跳转")
	@RequestMapping(value = "/commonIndex",method = RequestMethod.GET)
	public String commonIndex();
}
