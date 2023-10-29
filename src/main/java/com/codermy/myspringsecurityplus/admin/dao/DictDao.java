package com.codermy.myspringsecurityplus.admin.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codermy.myspringsecurityplus.admin.entity.SysDict;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author codermy
 * @since 2020-09-03
 */
@Mapper
public interface DictDao extends BaseMapper<SysDict> {

    /**
     * 模糊查询字典
     * @param sysDict 状态查询
     * @return
     */
    List<SysDict> getFuzzyDictByPage(SysDict sysDict);

    /**
     * 通过字典名称获取字典信息
     * @param dictName
     * @return
     */
    SysDict getDictByName(String dictName);

    /**
     * 通过id获得字典信息
     * @param dictId
     * @return
     */
    @Select("select di.dict_id,di.dict_name,di.description,di.sort,di.create_by,di.create_time,di.update_by ,di.update_time,di.enabled from sys_dict di  where di.dict_id = #{dictId} and di.enabled = 1")
    SysDict getDictById(Integer dictId);

    /**
     * 修改保存字典信息
     *
     * @param sysDict 岗位信息
     * @return 结果
     */
    int updateDict(SysDict sysDict);

    /**
     * 批量删除岗位信息
     *
     * @param dictIds 岗位ID数组
     * @return 结果
     */
    int deleteDictByIds(Integer[] dictIds);
}
