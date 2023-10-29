package com.codermy.myspringsecurityplus.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author codermy
 * @createTime 2020/8/23
 */
@Data
@TableName("sys_role_dept")
public class SysRoleDept extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8925514042332235875L;

    @TableId(type = IdType.AUTO)
    private Integer roleId;

    private Integer deptId;
}
