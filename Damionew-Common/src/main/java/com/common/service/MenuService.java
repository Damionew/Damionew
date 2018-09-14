package com.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.mapper.MenuMapper;
import com.common.model.Menu;

@Service
public class MenuService {
	@Autowired
	MenuMapper menuMapper;
	
	public List<Menu> menuList(){
		//1.查询父菜单
		List<Menu> parentMenus = menuMapper.parentMenu();
		//2.遍历父菜单
		for(Menu menu: parentMenus ) {
			String parentId = menu.getMenuId();
			//3.根据父菜单id查询子菜单
			List<Menu> childMenus = menuMapper.childMenu(parentId);
			menu.setChildMenus(childMenus);
		}
		return parentMenus;
	}
	
	
	public int menuAdd(String parentMenu,String menuName,String menuUrl) {
		// 首先判断是否有相同菜单名
		List<Menu> menuList = menuMapper.selectMenuByName(menuName);
		// 若有，则返回；若无，则继续执行
		if (menuList.size() > 0) {
			return 0;
		}
		// 菜单名未重复
		// 根据父菜单名获取父菜单id
		Menu parent = menuMapper.selectParentMenuByName(parentMenu);
		// 执行插入
		Menu childMenu = new Menu();
		childMenu.setMenuName(menuName);
		childMenu.setParentId(parent.getMenuId());
		childMenu.setUrl(menuUrl);
		childMenu.setMenuLevel(2);
		menuMapper.insertChildMenu(childMenu);
		return 1;
	}
	
	public int menuDelete(String menuName) {
		// 首先判断是否有相同菜单名
		List<Menu> menuList = menuMapper.selectMenuByName(menuName);
		// 若有，则返回；若无，则继续执行
		if (menuList.size() == 0) {
			return 0;
		}
		menuMapper.deleteChildMenu(menuName);
		return 1;
	}
	
	/** 
	 * 单个子菜单查询
	 * @param menuName
	 * @return
	 */
	public Menu menuQuery(String menuName) {
		// 首先判断是否有相同菜单名
		List<Menu> menuList = menuMapper.selectMenuByName(menuName);
		return menuList.get(0);
	}
	
	/**
	 * 子菜单更新
	 * @param menuName
	 * @return 状态值code
	 */
	public int menuUpdate(String oldMenuName,String newMenuName,String menuUrl) {
		// 首先判断是否有相同菜单名
		menuMapper.updateMenuByName(oldMenuName,newMenuName,menuUrl);
		return 1;
	}
}

