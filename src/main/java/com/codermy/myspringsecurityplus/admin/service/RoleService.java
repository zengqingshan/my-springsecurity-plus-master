package com.codermy.myspringsecurityplus.admin.service;

import com.codermy.myspringsecurityplus.admin.dto.RoleDto;
import com.codermy.myspringsecurityplus.admin.entity.SysRole;
import com.codermy.myspringsecurityplus.common.utils.Result;

/**
 * @author codermy
 * @createTime 2020/7/10
 */
public interface RoleService {
    /**
     * 返回角色
     * @param startPosition
     * @param limit
     * @param sysRole
     * @return
     */
    Result<SysRole> getFuzzyRolesByPage(Integer startPosition, Integer limit, SysRole sysRole);

    /**
     * 通过id获得角色信息
     * @param roleId
     * @return
     */
    SysRole getRoleById(Integer roleId);

    /**
     * 更新角色
     * @param roleDto
     * @return
     */
    Result update(RoleDto roleDto);

    /**
     * 数据权限
     * @param roleDto
     * @return
     */
    Result authDataScope(RoleDto roleDto);
    /**
     * 新建角色
     * @param roleDto
     * @return
     */
    Result save(RoleDto roleDto);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    Result<SysRole> delete(Integer roleId);

    /**
     * 获取全部角色
     * @return
     */
    Result<SysRole> getAllRoles();
}
