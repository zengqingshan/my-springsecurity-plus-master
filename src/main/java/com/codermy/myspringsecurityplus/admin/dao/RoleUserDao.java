package com.codermy.myspringsecurityplus.admin.dao;

import com.codermy.myspringsecurityplus.admin.entity.SysRoleUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/7/10
 */
@Mapper
public interface RoleUserDao {
    /**
     * 通过角色id返回所有用户
     * @param id
     * @return
     */
    @Select("select * from sys_role_user ru where ru.role_id = #{roleId} and ru.enabled = 1")
    List<SysRoleUser> listAllRoleUserByRoleId(Integer id);



    /**
     * 通过用户id查询权限id
     * @param userId
     * @return
     */
    @Select("select * from sys_role_user ru where ru.user_id = #{userId} and ru.enabled = 1")
    List<SysRoleUser> getMyRoleUserByUserId(Integer userId);

    /**
     * 通过用户id返回角色
     * @param intValue
     * @return
     */
    @Select("select * from sys_role_user ru where ru.user_id = #{userId} and ru.enabled = 1")
    SysRoleUser getRoleUserByUserId(int intValue);

    /**
     * 更新
     * @param sysRoleUser
     * @return
     */
    int updateMyRoleUser(SysRoleUser sysRoleUser);

    /**
     * 新建
     * @param sysRoleUser
     * @return
     */
    @Insert("insert into sys_role_user(user_id, role_id, enabled) values(#{userId}, #{roleId}, #{enabled})")
    int save(SysRoleUser sysRoleUser);

    /**
     * 删除
     * @param id
     * @return
     */
    @Update("UPDATE sys_role_user SET enabled = 0 WHERE user_id = #{userId}")
    int deleteRoleUserByUserId(Integer id);
}
