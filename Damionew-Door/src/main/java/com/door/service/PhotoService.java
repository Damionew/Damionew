package com.door.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.door.mapper.PhotoMapper;
import com.door.model.Photo;

/**
 * Photo服务处理
 * @author yinyunqi
 *
 */
@Service
public class PhotoService {
	@Autowired
	PhotoMapper photoMapper;
	
	public List<Photo> query_roll() {
		return photoMapper.query_roll();
	}
	
	public List<Photo> query_photo_detail(String album_id) {
		return photoMapper.query_photo_detail(album_id);
	}
	
}
