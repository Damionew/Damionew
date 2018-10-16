package com.door.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.door.model.Photo;

/**
 * Photo映射Mybatis接口
 * @author yinyunqi
 *
 */
@Mapper
public interface PhotoMapper {
	
	/**
	 * 查询滚动Photo
	 * @return
	 */
	public List<Photo> query_roll();
	
	public String query_album_cover(int album_id);
}
