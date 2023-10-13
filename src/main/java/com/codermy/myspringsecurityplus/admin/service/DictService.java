package com.codermy.myspringsecurityplus.admin.service;

import com.codermy.myspringsecurityplus.admin.entity.SysDict;
import com.codermy.myspringsecurityplus.common.exceptionhandler.MyException;
import com.codermy.myspringsecurityplus.common.utils.Result;

public interface DictService {

    /**
     * 分页返回字典
     * @return
     */
    Result<SysDict> getDictPage(Integer offectPosition, Integer limit, SysDict sysDict);

    /**
     * 通过字典名获取字典
     * @param dictName
     * @return
     */
    SysDict getDictByName(String dictName);

    /**
     * 校验字典名称
     *
     * @param sysDict 岗位信息
     * @return 结果
     */
    String checkDictNameUnique(SysDict sysDict);

    /**
     * 新增字典信息
     * @param sysDict 岗位信息
     * @return 结果
     */
    int insertDict(SysDict sysDict);

    /**
     * 通过id获得字典信息
     * @param dictId
     * @return
     */
    SysDict getDictById(Integer dictId);

    /**
     * 修改保存自带你信息
     *
     * @param sysDict 岗位信息
     * @return 结果
     */
    int updateDict(SysDict sysDict);

    /**
     * 批量删除岗位信息
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws MyException 异常
     */
    int deleteDictByIds(String ids)throws MyException;
}
