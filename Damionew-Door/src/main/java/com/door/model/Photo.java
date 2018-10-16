package com.door.model;

/**
 * Photo实体类
 * @author yinyunqi
 *
 */
public class Photo {
	private int photo_id;
	private String photo_name;
	private String photo_url;
	private String photo_desc;
	public int getPhoto_id() {
		return photo_id;
	}
	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}
	public String getPhoto_name() {
		return photo_name;
	}
	public void setPhoto_name(String photo_name) {
		this.photo_name = photo_name;
	}
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	public String getPhoto_desc() {
		return photo_desc;
	}
	public void setPhoto_desc(String photo_desc) {
		this.photo_desc = photo_desc;
	}
	
}
