package com.codermy.myspringsecurityplus.admin.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.codermy.myspringsecurityplus.admin.entity.SysRole;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/7/13
 */
public class RoleDto extends SysRole {
    private static final long serialVersionUID = -5784234789156935003L;

    @TableField(exist = false)
    private List<Integer> menuIds;

    @TableField(exist = false)
    private  List<Integer> deptIds;

    public List<Integer> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<Integer> deptIds) {
        this.deptIds = deptIds;
    }

    public List<Integer> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Integer> menuIds) {
        this.menuIds = menuIds;
    }

}
