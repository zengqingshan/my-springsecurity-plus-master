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
public class SysRoleMenu extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8925514045582235875L;

    @TableId
    private Integer roleId;

    private Integer menuId;
}
