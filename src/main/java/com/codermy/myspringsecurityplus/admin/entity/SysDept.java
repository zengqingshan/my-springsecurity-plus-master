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
@TableName("sys_dept")
public class SysDept extends BaseEntity {
    private static final long serialVersionUID = 8925514045582235633L;

    @TableId(type = IdType.AUTO)
    private Integer deptId;

    private Integer parentId;

    private String ancestors;

    private String deptName;

    private Integer sort;

    private Integer status;

}
