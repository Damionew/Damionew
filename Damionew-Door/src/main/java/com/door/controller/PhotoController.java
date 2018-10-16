package com.door.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.door.service.PhotoAlbumService;
import com.door.service.PhotoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * 相册控制器
 * @author yinyunqi
 *
 */


@Controller
@Api(value = "相册Controller" ,tags="相册")
public class PhotoController {
	
	@Autowired
	PhotoService photoService;
	@Autowired
	PhotoAlbumService photoAlbumService;
	
	@RequestMapping("/photo/index")
	@ApiOperation(value = "跳转相册页面",notes = "加载页面时加载数据库中的Photo")
	public String photoIndex(Model model) {
		
		model.addAttribute("roll", photoService.query_roll());
		model.addAttribute("album",photoAlbumService.queryAlbum());
		return "photoIndex";
	}
	
	@RequestMapping("/photo/detail")
	@ApiOperation(value = "跳转相册详细页面")
	public String photoDetail(Model model) {
		
		return "photoDetail";
	}
}
