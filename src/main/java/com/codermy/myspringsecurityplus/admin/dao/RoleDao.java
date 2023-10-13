package com.codermy.myspringsecurityplus.admin.dao;

import com.codermy.myspringsecurityplus.admin.dto.RoleDto;
import com.codermy.myspringsecurityplus.admin.entity.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/7/10
 */
@Mapper
public interface RoleDao {



    /**
     * 分页模糊查询角色
     * @param role
     * @return
     */
    List<SysRole> getFuzzyRolesByPage(SysRole role);

    /**
     * 通过id查询角色
     * @param roleId
     * @return
     */
    @Select("select r.role_id,r.role_name,r.data_scope,r.description,r.create_by,r.create_time,r.update_by,r.update_time,r.enabled from sys_role r where r.role_id = #{roleId} and r.enabled = 1")
    SysRole getRoleById(Integer roleId);

    /**
     * 更新角色
     * @param roleDto
     * @return
     */
    int update(RoleDto roleDto);

    /**
     * 新建角色
     * @param roleDto
     * @return
     */
    int saveRole(RoleDto roleDto);

    /**
     * 通过id删除角色
     * @param roleId
     * @return
     */
    @Update("UPDATE sys_role SET enabled = 0 WHERE role_id = #{roleId}")
    int delete(Integer roleId);

    /**
     * 返回所有角色
     * @return
     */
    @Select("select r.role_id,r.role_name,r.description from sys_role r where r.enabled=1")
    List<SysRole> getAllRoles();
}
