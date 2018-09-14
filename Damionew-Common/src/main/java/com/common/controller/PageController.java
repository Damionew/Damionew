/**
 * @author yinyunqi
 * @datetime 2018年5月30日
 * @Content 
 */
package com.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
	
	@RequestMapping(value = "/invoice",method = RequestMethod.GET)
	public String invoice() {
		return "invoice";
	}
	
	@RequestMapping(value = "/photoalbum",method = RequestMethod.GET)
	public String photoalbum() {
		return "photoalbum";
	}
	
	@RequestMapping(value = "/familyPhoto",method = RequestMethod.GET)
	public String familyPhoto() {
		return "familyPhoto";
	}
	@RequestMapping(value = "/timeline",method = RequestMethod.GET)
	public String timeLine() {
		return "timeline";
	}
	@RequestMapping(value = "/swagger",method = RequestMethod.GET)
	public String swagger() {
		return "swagger";
	}
	@RequestMapping(value = "/test",method = RequestMethod.GET)
	public String test() {
		return "test";
	}
}
