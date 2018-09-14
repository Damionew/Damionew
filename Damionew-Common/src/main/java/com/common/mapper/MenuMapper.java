/**
 * @author yinyunqi
 * @datetime 2018年6月23日
 * @Content 
 */
package com.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.common.model.Menu;

@Mapper
public interface MenuMapper {
	public List<Menu> parentMenu();
	
	public List<Menu> childMenu(String parentId); 
	
	public List<Menu> selectMenuByName(String menuName);
	
	public Menu selectParentMenuByName(String parentMenu);
	
	public void insertChildMenu(Menu menu);
	
	public void deleteChildMenu(String menuName);

	/**
	 * @param oldMenuName
	 * @param newMenuName
	 * @return
	 */
	public void updateMenuByName(@Param(value="oldMenuName")String oldMenuName, @Param(value="newMenuName")String newMenuName,@Param(value="menuUrl") String menuUrl);
}
