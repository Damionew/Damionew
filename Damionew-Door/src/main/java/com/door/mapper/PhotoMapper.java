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
	/**
	 * 查询相册封面
	 * @param album_id
	 * @return
	 */
	public String query_album_cover(int album_id);
	/**
	 * 查询指定相册图片
	 * @param album_id
	 * @return
	 */
	public List<Photo> query_photo_detail(String album_id);
}
