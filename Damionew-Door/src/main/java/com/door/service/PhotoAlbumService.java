package com.door.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.door.mapper.PhotoAlbumMapper;
import com.door.mapper.PhotoMapper;
import com.door.model.PhotoAlbum;

/**
 * 
 * 相册服务
 * @author yinyunqi
 *
 */
@Service
public class PhotoAlbumService {
	@Autowired
	PhotoMapper photoMappper;
	@Autowired
	PhotoAlbumMapper photoAlbumMapper;
	
	public List<Map<String,String>> queryAlbum(){
		// 查询相册
		List<PhotoAlbum> photoAlbumList = photoAlbumMapper.query_album();
		// 存放相册信息到Map中
		List<Map<String, String>> albumMap = new ArrayList<Map<String,String>>();
		// 每个相册添加相册封面
		for (PhotoAlbum photoAlbum : photoAlbumList) {
			String photo_url = photoMappper.query_album_cover(photoAlbum.getAlbum_id());
			if (photo_url == null) {
				photo_url = "/images/1.jpg";
			}
			Map<String, String> map = new HashMap<>();
			map.put("photo_url", photo_url);
			map.put("album_name", photoAlbum.getAlbum_name());
			map.put("album_desc", photoAlbum.getAlbum_desc());
			albumMap.add(map);
		}
		return albumMap;
	}
}
