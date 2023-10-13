package com.codermy.myspringsecurityplus.admin.service;

import com.codermy.myspringsecurityplus.admin.dto.JobQueryDto;
import com.codermy.myspringsecurityplus.admin.entity.SysJob;
import com.codermy.myspringsecurityplus.common.exceptionhandler.MyException;
import com.codermy.myspringsecurityplus.common.utils.Result;


import java.util.List;

/**
 * @author codermy
 * @createTime 2020/8/19
 */
public interface JobService {
    /**
     * 返回岗位
     * @param offectPosition
     * @param limit
     * @param jobQueryDto
     * @return
     */
    Result<SysJob> getJobAll(Integer offectPosition, Integer limit, JobQueryDto jobQueryDto);

    /**
     * 新增岗位信息
     * @param job 岗位信息
     * @return 结果
     */
    int insertJob(SysJob job);

    /**
     * 校验岗位名称
     *
     * @param job 岗位信息
     * @return 结果
     */
    String checkJobNameUnique(SysJob job);

    /**
     * 通过id获得岗位信息
     * @param jobId
     * @return
     */
    SysJob getJobById(Integer jobId);

    /**
     * 批量删除岗位信息
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws MyException 异常
     */
    int deleteJobByIds(String ids) throws MyException;

    /**
     * 通过岗位ID查询岗位使用数量
     *
     * @param jobId 岗位ID
     * @return 结果
     */
     int countUserJobtById(Integer jobId);

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
     List<SysJob> selectJobAll();
    /**
     * 根据用户ID查询岗位
     *
     * @param userId 用户ID
     * @return 岗位列表
     */
     List<SysJob> selectJobsByUserId(Integer userId);

     /**
      * 修改保存岗位信息
      *
      * @param sysJob 岗位信息
      * @return 结果
      */
    int updateJob(SysJob sysJob);

    /**
     * 修改岗位状态
     * @param myUser
     * @return
     */
    int changeStatus(SysJob myUser);
}
