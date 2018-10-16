package com.door.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.door.model.PhotoAlbum;

/**
 * 相册数据接口
 * @author yinyunqi
 *
 */
@Mapper
public interface PhotoAlbumMapper {
	
	public List<PhotoAlbum> query_album();
}
