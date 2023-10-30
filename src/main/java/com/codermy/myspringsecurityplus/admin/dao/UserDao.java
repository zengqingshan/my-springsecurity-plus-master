package com.codermy.myspringsecurityplus.admin.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codermy.myspringsecurityplus.admin.entity.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/7/10
 */
@Mapper
public interface UserDao extends BaseMapper<SysUser> {
    /**
     * 分页返回所有用户
     * @param sysUser
     * @return
     */
     List<SysUser> getFuzzyUserByPage(SysUser sysUser);

    //计算所有用户数量
    // @Select("select count(*) from My_user")
    // Long countAllUser();

    /**
     *
     * 通过id返回用户
     * @param userId
     * @return
     */
    @Select("select u.user_id,u.type,u.dept_id,u.user_name,u.password,u.nick_name,u.phone,u.email,u.status,u.create_by,u.create_time,u.update_by,u.update_time,u.enabled from sys_user u where u.user_id = #{userId} and u.enabled = 1")
    SysUser getUserById(Integer userId);

    /**
     * 通过手机返回用户
     * @param phone
     * @return
     */
    SysUser checkPhoneUnique(String phone);

    /**
     * 通过用户名返回用户
     * @param userName
     * @return
     */
    SysUser checkUsernameUnique(String userName);
    /**
     * 更新用户
     * @param sysUser
     * @return
     */
    int updateUser(SysUser sysUser);

    /**
     * 通过id删除用户
     * @param userId
     * @return
     */
    @Update("update sys_user set enabled = 0 where user_id = #{userId}")
    int deleteUserById(Integer userId);



    /**
     *  根据用户名查询用户
     * @param userName
     * @return
     */
    @Select("select * from sys_user t where t.user_name = #{userName} and t.enabled = 1")
    SysUser getUser(String userName);


}
