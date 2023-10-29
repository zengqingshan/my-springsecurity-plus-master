package com.codermy.myspringsecurityplus.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codermy.myspringsecurityplus.admin.dto.MenuDto;
import com.codermy.myspringsecurityplus.admin.dto.MenuIndexDto;
import com.codermy.myspringsecurityplus.admin.entity.SysMenu;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/7/10
 */
@SuppressWarnings("ALL")
@Mapper
public interface MenuDao extends BaseMapper<SysMenu> {
    /**
     * 模糊查询菜单
     * @param queryName 查询的表题
     * @param queryType 查询类型
     * @return
     */
    @SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
    List<SysMenu> getFuzzyMenu(String queryName, Integer queryType);

    /**
     * 通过id查询菜单
     * @param menuId
     * @return
     */
    @Select("select m.menu_id,m.parent_id,m.menu_name,m.icon,m.url,m.permission,m.sort,m.type,m.create_by,m.create_time,m.update_by,m.update_time,m.enabled from sys_menu m where m.menu_id = #{menuId} and enabled = 1")
    SysMenu getMenuById(Integer menuId);

    /**
     * 菜单树
     * @return
     */
    @Select("select m.menu_id,m.parent_id,m.menu_name from sys_menu m where m.enabled=1")
    @Result(property = "title",column = "menu_name")
    @Result(property = "id",column = "menu_id")
    List<MenuDto> buildAll();

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    int update(SysMenu menu);

//    /**
//     * 新建菜单
//     * @param menu
//     * @return
//     */
//    @Options(useGeneratedKeys = true, keyProperty = "menuId")
//    @Insert("insert into sys_menu(parent_id, menu_name, icon, url, permission, sort, type, enabled)values(#{parentId}, #{menuName}, #{icon}, #{url}, #{permission}, #{sort}, #{type}, #{enabled})")
//    int save(SysMenu menu);

    /**
     * 通过id删除菜单
     * @param menuId
     * @return
     */
    @Update("UPDATE sys_menu SET eanbled = 0 WHERE menu_id = #{menuId}")
    int deleteById(Integer menuId);

    /**
     * 通过父节点删除子菜单
     * @param parentId
     * @return
     */
    @Update("UPDATE sys_menu SET eanbled = 0 WHERE parent_id = #{parentId}")
    int deleteByParentId(Integer parentId);

    /**
     * 通过父节点返回字节点
     * @param parentId
     * @return
     */
    @Select("select m.menu_id from sys_menu m where parent_id = #{parentId} and m.enabled = 1")
    List<Integer> selectByParentId(Integer parentId);

    /**
     * 通过角色id返回菜单
     * @param roleId
     * @return
     */
    @Select("select m.menu_id,m.parent_id,m.menu_name from sys_menu m inner join sys_role_menu rm on m.menu_id = rm.menu_id where rm.role_id = #{roleId} and m.enabled = 1")
    @Result(property = "title",column = "menu_name")
    @Result(property = "id",column = "menu_id")
    List<MenuDto> listByRoleId(Integer roleId);

    /**
     * 通过用户id返回菜单
     * @param userId
     * @return
     */
    List<MenuIndexDto> listByUserId(@Param("userId")Integer userId);
}
