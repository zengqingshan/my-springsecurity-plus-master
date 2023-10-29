package com.codermy.myspringsecurityplus.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author codermy
 * @createTime 2020/8/21
 */
@Data
@TableName("sys_user_job")
public class SysUserJob extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8925514045582235321L;

    @TableId(type = IdType.AUTO)
    private Integer userId;

    private Integer jobId;
}
