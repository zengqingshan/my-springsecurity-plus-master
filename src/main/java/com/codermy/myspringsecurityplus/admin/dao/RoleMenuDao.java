package com.codermy.myspringsecurityplus.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codermy.myspringsecurityplus.admin.entity.SysRoleMenu;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/7/10
 */
@Mapper
public interface RoleMenuDao extends BaseMapper<SysRoleMenu> {
    /**
     * 通过id删除rolemenu
     * @param roleId
     * @return
     */
    @Update("UPDATE sys_role_menu SET enabled = 0 WHERE role_id = #{roleId}")
    int deleteRoleMenu(Integer roleId);

//    /**
//     * 新建角色与menu的联系
//     * @param roleId
//     * @param menuIds
//     */
//    void save(@Param("roleId")Integer roleId,@Param("menuIds") List<Integer> menuIds);

    /**
     * 通过role_id计算权限数量
     * @param id
     * @return
     */
    @Select("select count(*) from sys_role_menu t where t.menu_id = #{menuId} and t.enabled = 1")
    Integer countRoleMenuByRoleId(Integer id);
}
