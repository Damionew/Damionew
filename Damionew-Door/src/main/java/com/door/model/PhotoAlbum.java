package com.door.model;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public class PhotoAlbum {
	private int album_id;
	private String album_name;
	private String album_date;
	private String album_desc;
	public int getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(int album_id) {
		this.album_id = album_id;
	}
	public String getAlbum_name() {
		return album_name;
	}
	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}
	public String getAlbum_date() {
		return album_date;
	}
	public void setAlbum_date(String album_date) {
		this.album_date = album_date;
	}
	public String getAlbum_desc() {
		return album_desc;
	}
	public void setAlbum_desc(String album_desc) {
		this.album_desc = album_desc;
	}
	
	
}
