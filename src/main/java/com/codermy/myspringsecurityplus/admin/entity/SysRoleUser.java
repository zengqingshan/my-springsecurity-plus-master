package com.codermy.myspringsecurityplus.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author codermy
 * @createTime 2020/7/10
 */
@Data
@TableName("sys_role_user")
public class SysRoleUser extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 8545514045582235838L;

    @TableId(type = IdType.AUTO)
    private Integer userId;

    private Integer roleId;
}
