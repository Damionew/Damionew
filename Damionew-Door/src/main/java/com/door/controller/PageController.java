package com.door.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;

@Controller
public class PageController {
	
	@ApiOperation(value = "相册",notes ="相册主页")
	@RequestMapping(value = "/photoIndex",method = RequestMethod.GET)
	public String photoIndex(){
		return "photoIndex";
	}
}
