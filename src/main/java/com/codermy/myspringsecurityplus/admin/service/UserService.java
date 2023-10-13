package com.codermy.myspringsecurityplus.admin.service;


import com.codermy.myspringsecurityplus.admin.entity.SysUser;
import com.codermy.myspringsecurityplus.common.utils.Result;

/**
 * @author codermy
 * @createTime 2020/7/10
 */
public interface UserService {
    /**
     * 返回用户列表，包含模糊查询功能
     * @param offectPosition  开始查询页面
     * @param limit    每页限制大小
     * @param sysUser   当需要模糊查询时，封装成实体对象再进行查询
     * @return
     */
    Result<SysUser> getAllUsersByPage(Integer offectPosition, Integer limit, SysUser sysUser);

    /**
     * 根据id返回用户信息
     * @param id
     * @return
     */
    SysUser getUserById(Integer id);

    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */
    public void checkUserAllowed(SysUser user);

    /**
     * 通过手机查询用户
     * @param user
     * @return
     */
    String checkPhoneUnique(SysUser user);

    /**
     * 通过用户名查询用户
     * @param user
     * @return
     */
    String checkUserNameUnique(SysUser user);

    /**
     * 更新用户
     * @param sysUser
     * @param roleId
     * @return
     */
    Result<SysUser> updateUser(SysUser sysUser, Integer roleId);

    /**
     * 用户状态修改
     *
     * @param user 用户信息
     * @return 结果
     */
    int changeStatus(SysUser user);

    /**
     * 新建用户
     * @param sysUser
     * @param roleId
     * @return
     */
    Result<SysUser> save(SysUser sysUser, Integer roleId);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    int deleteUser(Integer userId);
    /**
     *  根据用户名查询用户
     * @param userName
     * @return
     */
    SysUser getUserByName(String userName);
}
