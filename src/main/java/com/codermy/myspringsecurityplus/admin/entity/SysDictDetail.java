package com.codermy.myspringsecurityplus.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据字典
 * </p>
 *
 * @author codermy
 * @since 2020-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysDict对象", description="数据字典")
@TableName("sys_dict_detail")
public class SysDictDetail extends BaseEntity{

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Integer Id;

    @ApiModelProperty(value = "字典id")
    private Integer dictId;

    @ApiModelProperty(value = "字典标签")
    private String label;

    @ApiModelProperty(value = "字典值")
    private String value;

    @ApiModelProperty(value = "字典详情排序")
    private Integer sort;
}
