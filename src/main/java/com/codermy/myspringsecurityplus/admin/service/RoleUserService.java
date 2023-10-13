package com.codermy.myspringsecurityplus.admin.service;

import com.codermy.myspringsecurityplus.admin.entity.SysRoleUser;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/7/13
 */
public interface RoleUserService {
    /**
     * 返回用户拥有的角色
     * @param userId
     * @return
     */
    List<SysRoleUser> getMyRoleUserByUserId(Integer userId);
}
