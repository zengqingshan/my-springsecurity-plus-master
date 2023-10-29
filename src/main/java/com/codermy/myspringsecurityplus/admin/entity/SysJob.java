package com.codermy.myspringsecurityplus.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author codermy
 * @createTime 2020/8/19
 */
@Data
@TableName("sys_job")
public class SysJob extends BaseEntity {
    private static final long serialVersionUID = 8925514045582234222L;

    @TableId(type = IdType.AUTO)
    private Integer jobId;

    private String jobName;

    private Integer status;

    private Integer sort;

    /** 用户是否存在此岗位标识 默认不存在 */
    private boolean flag = false;
}
