package com.codermy.myspringsecurityplus.admin.dao;

import com.codermy.myspringsecurityplus.admin.entity.SysJob;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/8/19
 */
@Mapper
public interface JobDao {
    /**
     * 模糊查询岗位
     * @param queryName 查询的名称
     * @param queryStatus 状态查询
     * @return
     */
    List<SysJob> getFuzzyJob(String queryName, Integer queryStatus);

    /**
     * 新增岗位信息
     * @param job 岗位信息
     * @return 结果
     */
    @Insert("INSERT INTO sys_job(job_name,status,sort,enabled) values(#{jobName},#{status},#{sort},#{enanled})")
    int insertDept(SysJob job);


    /**
     * 校验岗位名称
     * @param name 岗位名称
     * @return 结果
     */
    SysJob checkJobNameUnique(String name);
    /**
     * 通过id查询岗位信息
     * @param jobId
     * @return
     */
    @Select("select j.job_id,j.job_name,j.status,j.sort,j.create_by,j.create_time,j.update_by,j.update_time,j.enabled from sys_job j where j.job_id = #{jobId} and j.enabled=1")
    SysJob getJobById(Integer jobId);

    /**
     * 批量删除岗位信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteJobByIds(Integer[] ids);

    /**
     * 根据用户ID查询岗位
     *
     * @param userId 用户ID
     * @return 岗位列表
     */
    List<SysJob> selectJobsByUserId(Integer userId);

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    List<SysJob> selectJobAll();

    /**
     * 修改岗位信息
     *
     * @param sysJob 岗位信息
     * @return 结果
     */
    int updateJob(SysJob sysJob);
}
