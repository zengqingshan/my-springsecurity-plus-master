package com.codermy.myspringsecurityplus.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codermy.myspringsecurityplus.admin.entity.SysUserJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/8/21
 */
@Mapper
public interface UserJobDao extends BaseMapper<SysUserJob> {

    /**
     * 通过岗位ID查询岗位使用数量
     *
     * @param jobId 岗位ID
     * @return 结果
     */
    @Select("select count(1) from my_user_job where job_id=#{jobId} and enabled = 1")
    int countUserJobtById(Integer jobId);

    /**
     * 批量新增用户岗位信息
     *
     * @param userJobList 用户角色列表
     * @return 结果
     */
    int batchUserJob(List<SysUserJob> userJobList);

    /**
     * 通过用户ID删除用户和岗位关联
     *
     * @param id 用户ID
     * @return 结果
     */
    int deleteUserJobByUserId(Integer id);
}
