package com.codermy.myspringsecurityplus.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codermy.myspringsecurityplus.admin.entity.SysDictDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author codermy
 * @since 2020-09-03
 */
@Mapper
public interface DictDetailDao extends BaseMapper<SysDictDetail> {

    List<SysDictDetail> getDictDetail(Integer dictId);
//    /**
//     * 插入字典详情
//     * @param sysDictDetail
//     * @return
//     */
//    @Insert("INSERT INTO sys_dict_detail(dict_id,label,value, sort, enabled)values(#{dictId},#{label},#{value},#{sort},#{enabled})")
//    int insertDictDetail(SysDictDetail sysDictDetail);

    /**
     * 通过id获得字典详情信息
     * @param id
     * @return
     */
    @Select("select did.id,did.dict_id,did.label,did.value,did.sort,did.create_by,did.create_time,did.update_by,did.update_time,did.enabled from sys_dict_detail did  where did.id = #{id} AND did.enabled = 1")
    SysDictDetail getDictDetailById(Integer id);

    /**
     * 修改保存字典详情信息
     *
     * @param sysDictDetail 岗位信息
     * @return 结果
     */
    int updateDictDetail(SysDictDetail sysDictDetail);

    /**
     * 批量删除字典详情
     *
     * @param dictDetailIds 需要删除的数据ID
     * @return 结果
     */
    int deleteDictDetailByIds(Integer[] dictDetailIds);

    /**
     *
     * 根据字典id删除字典详情
     * @param dictId 字典ID
     * @return
     */
    int deleteDictDetailByDictId(Integer dictId);
}
