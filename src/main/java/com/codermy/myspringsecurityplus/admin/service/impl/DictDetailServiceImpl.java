package com.codermy.myspringsecurityplus.admin.service.impl;

import cn.hutool.core.convert.Convert;
import com.codermy.myspringsecurityplus.admin.dao.DictDetailDao;
import com.codermy.myspringsecurityplus.admin.entity.SysDict;
import com.codermy.myspringsecurityplus.admin.entity.SysDictDetail;
import com.codermy.myspringsecurityplus.admin.service.DictDetailService;
import com.codermy.myspringsecurityplus.admin.service.DictService;
import com.codermy.myspringsecurityplus.common.utils.Result;
import com.codermy.myspringsecurityplus.common.utils.ResultCode;
import com.codermy.myspringsecurityplus.common.utils.SecurityUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DictDetailServiceImpl implements DictDetailService {
    @Autowired
    private DictService dictService;

    @Autowired
    private DictDetailDao dictDetailDao;

    @Override
    public Result<SysDictDetail> getDictByName(Integer offectPosition, Integer limit, String dictName) {
        SysDict dictByName =dictService.getDictByName(dictName);
        Integer dictId = dictByName.getDictId();
        Page page = PageHelper.offsetPage(offectPosition,limit);
        List<SysDictDetail> fuzzyDictDetailByPage = getDictDetail(dictId);
        return Result.ok().count(page.getTotal()).data(fuzzyDictDetailByPage).code(ResultCode.TABLE_SUCCESS);
    }

    @Override
    public List<SysDictDetail> getDictDetail(Integer dictId) {
        return dictDetailDao.getDictDetail(dictId);
    }

    @Override
    public int insertDictDetail(SysDictDetail sysDictDetail) {
        return dictDetailDao.insert(sysDictDetail);
    }

    @Override
    public SysDictDetail getDictDetailById(Integer id) {
        return dictDetailDao.getDictDetailById(id);
    }

    @Override
    public int updateDictDetail(SysDictDetail sysDictDetail) {
        sysDictDetail.setUpdateBy(SecurityUtils.getCurrentUsername());
        sysDictDetail.setUpdateTime(new Date());
        return dictDetailDao.updateDictDetail(sysDictDetail);
    }

    @Override
    public int deleteDictDetailByIds(String ids) {
        Integer[] dictDetailIds = Convert.toIntArray(ids);
        return dictDetailDao.deleteDictDetailByIds(dictDetailIds);
    }

    @Override
    public int deleteDictDetailByDictId(Integer dictId) {
        return dictDetailDao.deleteDictDetailByDictId(dictId);
    }
}
