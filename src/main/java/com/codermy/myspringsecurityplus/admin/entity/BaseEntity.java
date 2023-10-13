package com.codermy.myspringsecurityplus.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CodePoorFish
 * @createTime 2023/10/10
 */
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8925514045582235838L;

    /*
    * @JsonFormat注解的作用是格式化时间类型数据传输时的格式，以自己想要的格式来展示日期，同时也设置时区，避免时间展示与想要的结果产生误差。
    * @DateTimeFormat注解作用则是将前端传来的字符串类型的日期转为后台需要的时间类型结果，不加此注解，请求会报错400，请求参数错误，对于此类错误要注意int类型数据传输也是一样。
    */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE, select = false)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    private Integer enabled = 1;

    /** 请求参数 */
    private Map<String, Object> params;

    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

    public Map<String, Object> get1Params() {
        return params;
    }


}
