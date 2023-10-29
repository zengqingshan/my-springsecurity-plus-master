package com.codermy.myspringsecurityplus.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author codermy
 * @createTime 2020/7/10
 */
@Data
@TableName("sys_menu")
public class SysMenu extends BaseEntity{

    private static final long serialVersionUID = -6525908145032868815L;

    @TableId(type = IdType.AUTO)
    private Integer menuId;

    private Integer parentId;

    private String menuName;

    private String icon;

    private Integer type;

    private String url;

    private String permission;

    private Integer sort;
}
