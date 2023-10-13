package com.codermy.myspringsecurityplus.admin.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.codermy.myspringsecurityplus.admin.dao.JobDao;
import com.codermy.myspringsecurityplus.admin.dao.UserJobDao;
import com.codermy.myspringsecurityplus.admin.dto.JobQueryDto;
import com.codermy.myspringsecurityplus.admin.entity.SysJob;
import com.codermy.myspringsecurityplus.admin.service.JobService;
import com.codermy.myspringsecurityplus.common.exceptionhandler.MyException;
import com.codermy.myspringsecurityplus.common.utils.Result;
import com.codermy.myspringsecurityplus.common.utils.ResultCode;
import com.codermy.myspringsecurityplus.common.utils.UserConstants;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/8/19
 */
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobDao jobDao;
    @Autowired
    private UserJobDao userJobDao;

    @Override
    public Result<SysJob> getJobAll(Integer offectPosition, Integer limit, JobQueryDto jobQueryDto) {
        Page page = PageHelper.offsetPage(offectPosition,limit);
        List<SysJob> fuzzyJob = jobDao.getFuzzyJob(jobQueryDto.getQueryName(), jobQueryDto.getQueryStatus());
        return Result.ok().count(page.getTotal()).data(fuzzyJob).code(ResultCode.TABLE_SUCCESS);
    }

    @Override
    public int insertJob(SysJob job) {
        return jobDao.insertDept(job);
    }

    /**
     * 校验岗位名称是否唯一
     *
     * @param job 岗位信息
     * @return 结果
     */
    @Override
    public String checkJobNameUnique(SysJob job) {
        SysJob info = jobDao.checkJobNameUnique(job.getJobName());
        if (ObjectUtil.isNotEmpty(info) && !info.getJobId().equals (job.getJobId())){
            return UserConstants.JOB_NAME_NOT_UNIQUE;
        }
        return UserConstants.JOB_NAME_UNIQUE;
    }

    @Override
    public SysJob getJobById(Integer jobId) {
        return jobDao.getJobById(jobId);
    }

    @Override
    public int deleteJobByIds(String ids) throws MyException {
        Integer[] jobIds = Convert.toIntArray(ids);
        for (Integer jobid:jobIds){
            SysJob job = getJobById(jobid);
            if (countUserJobtById(jobid)>0){
                throw new MyException(ResultCode.ERROR,job.getJobName()+ "已分配,不能删除");
            }
        }
        return jobDao.deleteJobByIds(jobIds);
    }

    @Override
    public int countUserJobtById(Integer jobId) {
        return userJobDao.countUserJobtById(jobId);
    }

    @Override
    public List<SysJob> selectJobAll() {
        return jobDao.selectJobAll();
    }

    @Override
    public List<SysJob> selectJobsByUserId(Integer userId) {
        List<SysJob> userJobs = jobDao.selectJobsByUserId(userId);
        List<SysJob>jobs =jobDao.selectJobAll();
        for (SysJob job : jobs)
        {
            for (SysJob userJob : userJobs)
            {
                if (job.getJobId().equals(userJob.getJobId()))
                {
                    job.setFlag(true);
                    break;
                }
            }
        }
        return jobs;
    }

    @Override
    public int updateJob(SysJob sysJob) {
        return jobDao.updateJob(sysJob);
    }

    @Override
    public int changeStatus(SysJob sysJob) {
        return jobDao.updateJob(sysJob);
    }
}
