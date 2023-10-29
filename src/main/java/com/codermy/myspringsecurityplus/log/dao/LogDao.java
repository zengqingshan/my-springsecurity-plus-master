package com.codermy.myspringsecurityplus.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codermy.myspringsecurityplus.log.dto.ErrorLogDto;
import com.codermy.myspringsecurityplus.log.dto.LogDto;
import com.codermy.myspringsecurityplus.log.dto.LogQuery;
import com.codermy.myspringsecurityplus.log.entity.SysLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/8/8
 */
@Mapper
public interface LogDao extends BaseMapper<SysLog> {

    /**
     * 分页返回所有用户日志
     * @param logQuery 查询条件
     * @return
     */
    List<LogDto> getFuzzyLogByPage( @Param("logQuery") LogQuery logQuery);


    /**
     * 分页返回所有错误日志
     * @param logQuery 查询条件
     * @return
     */
    List<ErrorLogDto> getFuzzyErrorLogByPage(@Param("logQuery") LogQuery logQuery);


    /**
     * 删除所有日志
     * @param type 日志类型
     */
    @Delete("DELETE FROM sys_log WHERE type = #{type}")
    void delAllByInfo(String type);
}
