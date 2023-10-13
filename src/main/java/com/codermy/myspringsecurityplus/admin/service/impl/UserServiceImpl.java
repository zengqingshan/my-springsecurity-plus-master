package com.codermy.myspringsecurityplus.admin.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.codermy.myspringsecurityplus.admin.annotation.DataPermission;
import com.codermy.myspringsecurityplus.admin.dao.RoleUserDao;
import com.codermy.myspringsecurityplus.admin.dao.UserDao;
import com.codermy.myspringsecurityplus.admin.dao.UserJobDao;

import com.codermy.myspringsecurityplus.admin.entity.SysRoleUser;
import com.codermy.myspringsecurityplus.admin.entity.SysUser;
import com.codermy.myspringsecurityplus.admin.entity.SysUserJob;
import com.codermy.myspringsecurityplus.admin.service.UserService;
import com.codermy.myspringsecurityplus.common.exceptionhandler.MyException;
import com.codermy.myspringsecurityplus.common.utils.Result;
import com.codermy.myspringsecurityplus.common.utils.ResultCode;
import com.codermy.myspringsecurityplus.common.utils.SecurityUtils;
import com.codermy.myspringsecurityplus.common.utils.UserConstants;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author codermy
 * @createTime 2020/7/10
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleUserDao roleUserDao;

    @Autowired
    private UserJobDao userJobDao;

    // 获取所有用户
    @Override
    @DataPermission(deptAlias = "d", userAlias = "u")
    public Result<SysUser> getAllUsersByPage(Integer offectPosition, Integer limit, SysUser sysUser) {
        Page page = PageHelper.offsetPage(offectPosition,limit);
        List<SysUser> fuzzyUserByPage = userDao.getFuzzyUserByPage(sysUser);
        return Result.ok().count(page.getTotal()).data(fuzzyUserByPage).code(ResultCode.TABLE_SUCCESS);
    }

    // 根据用户ID获取用户信息
    @Override
    public SysUser getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    // 校验用户是否允许操作
    @Override
    public void checkUserAllowed(SysUser user) {
        if (!StringUtils.isEmpty(user.getUserId()) && user.isAdmin())
        {
            throw new MyException(ResultCode.ERROR,"不允许操作超级管理员用户");
        }
    }

    // 通过手机查询用户，用于判断用户手机号是否唯一
    @Override
    public String checkPhoneUnique(SysUser sysUser) {
        Integer userId = ObjectUtil.isEmpty(sysUser.getUserId()) ? -1: sysUser.getUserId();
        SysUser info = userDao.checkPhoneUnique(sysUser.getPhone());
        if (ObjectUtil.isNotEmpty(info) && !info.getUserId().equals(userId))
        {
            return UserConstants.USER_PHONE_NOT_UNIQUE;
        }
        return UserConstants.USER_PHONE_UNIQUE;
    }

    // 通过用户名查询用户，用于判断用户名是否唯一
    @Override
    public String checkUserNameUnique(SysUser sysUser) {
        Integer userId = ObjectUtil.isEmpty(sysUser.getUserId()) ? -1: sysUser.getUserId();
        SysUser info = userDao.checkUsernameUnique(sysUser.getUserName());
        if (ObjectUtil.isNotEmpty(info) && !info.getUserId().equals(userId))
        {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }

    // 更新用户
    @Override
    public Result<SysUser> updateUser(SysUser sysUser, Integer roleId) {
        if (roleId!=null){
            userDao.updateUser(sysUser);
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setUserId(sysUser.getUserId());
            sysRoleUser.setRoleId(roleId);
            if(roleUserDao.getRoleUserByUserId(sysUser.getUserId())!=null){
                sysRoleUser.setUpdateBy(SecurityUtils.getCurrentUsername());
                roleUserDao.updateMyRoleUser(sysRoleUser);
            }else {
                sysRoleUser.setCreateBy(SecurityUtils.getCurrentUsername());
                roleUserDao.save(sysRoleUser);
            }
            userJobDao.deleteUserJobByUserId(sysUser.getUserId());
            insertUserPost(sysUser);
            return Result.ok().message("更新成功");
        }else {
            return Result.error().message("更新失败");
        }
    }

    @Override
    public int changeStatus(SysUser user) {
        return userDao.updateUser(user);
    }

    // 新建用户
    @Override
    public Result<SysUser> save(SysUser sysUser, Integer roleId) {
        if(roleId!= null){
//            userDao.save(sysUser);
            userDao.insert(sysUser);
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setRoleId(roleId);
            sysRoleUser.setUserId(sysUser.getUserId().intValue());
            roleUserDao.save(sysRoleUser);
            insertUserPost(sysUser);
            return Result.ok().message("添加成功，初始密码123456");
        }

        return Result.error().message("添加失败");
    }

    @Override
    public int deleteUser(Integer userId) {
        checkUserAllowed(new SysUser(userId));
        roleUserDao.deleteRoleUserByUserId(userId);
        userJobDao.deleteUserJobByUserId(userId);
        return userDao.deleteUserById(userId);
    }

    @Override
    public SysUser getUserByName(String userName) {
        return userDao.getUser(userName);
    }


    /**
     * 新增用户岗位信息
     *
     * @param user 用户对象
     */
    public void insertUserPost(SysUser user)
    {
        Integer[] jobs = user.getJobIds();

        if (ArrayUtil.isNotEmpty(jobs))
        {
            // 新增用户与岗位管理
            List<SysUserJob> list = new ArrayList<SysUserJob>();
            for (Integer jobId : jobs)
            {
                SysUserJob up = new SysUserJob();
                up.setUserId(user.getUserId());
                up.setJobId(jobId);
                list.add(up);
            }
            if (list.size() > 0)
            {
                userJobDao.batchUserJob(list);
            }
        }
        return;
    }
}
