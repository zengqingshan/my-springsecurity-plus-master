package com.codermy.myspringsecurityplus.admin.dao;


import com.codermy.myspringsecurityplus.admin.entity.MyUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/7/10
 */
@Mapper
public interface UserDao {



    /**
     * 分页返回所有用户
     * @param myUser
     * @return
     */
     List<MyUser> getFuzzyUserByPage( MyUser myUser);

    //计算所有用户数量
    // @Select("select count(*) from My_user")
    // Long countAllUser();

    /**
     *
     * 通过id返回用户
     * @param userId
     * @return
     */
    @Select("select u.user_id,u.type,u.dept_id,u.user_name,u.password,u.nick_name,u.phone,u.email,u.status,u.create_by,u.create_time,u.update_by,u.update_time,u.enabled from sys_user u where u.user_id = #{userId}")
    MyUser getUserById(Integer userId);

    /**
     * 通过手机返回用户
     * @param phone
     * @return
     */
    MyUser checkPhoneUnique(String phone);

    /**
     * 通过用户名返回用户
     * @param userName
     * @return
     */
    MyUser checkUsernameUnique(String userName);
    /**
     * 更新用户
     * @param myUser
     * @return
     */
    int updateUser(MyUser myUser);



    /**
     * 插入用户
     * @param myUser
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    @Insert("insert into sys_user(type, dept_id, user_name, password, nick_name, phone, email, status, create_by, create_time, update_by, update_time, enabled) values(#{type}, #{deptId}, #{userName}, #{password}, #{nickName}, #{phone}, #{email}, #{status}, #{createBy}, now(), #{update_by}, now(), #{enabled})")
    int save(MyUser myUser);

    /**
     * 通过id删除用户
     * @param userId
     * @return
     */
    @Delete("delete from sys_user where user_id = #{userId}")
    int deleteUserById(Integer userId);



    /**
     *  根据用户名查询用户
     * @param userName
     * @return
     */
    @Select("select * from sys_user t where t.user_name = #{userName}")
    MyUser getUser(String userName);


}
