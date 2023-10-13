package com.codermy.myspringsecurityplus.log.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author codermy
 * @createTime 2020/8/4
 */
@Data
public class SysLog implements Serializable {

    private Long logId;

    /** 操作用户 */
    private String userName;

    /** 描述 */
    private String description;

    /** 方法名 */
    private String method;

    /** 请求ip */
    private String ip;

    /** 异常详细  */
    private String exceptionDetail;

    /** 异常类型 */
    private String type;

    // /** 地址 */
    // private String ipAddress;

    /** 参数*/
    private String params;

    /** 浏览器  */
    private String browser;

    /** 请求耗时 */
    private Long time;

    /** 创建人 */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /** 创建日期 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
//    private Date createTime = new Date();

    public SysLog(String type, Long time) {
        this.type = type;
        this.time = time;
    }

}
