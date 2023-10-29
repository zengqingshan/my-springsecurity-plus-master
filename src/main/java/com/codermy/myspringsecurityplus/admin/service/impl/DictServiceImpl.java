package com.codermy.myspringsecurityplus.admin.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.codermy.myspringsecurityplus.admin.dao.DictDao;
import com.codermy.myspringsecurityplus.admin.dao.DictDetailDao;
import com.codermy.myspringsecurityplus.admin.entity.SysDict;
import com.codermy.myspringsecurityplus.admin.service.DictService;
import com.codermy.myspringsecurityplus.common.exceptionhandler.MyException;
import com.codermy.myspringsecurityplus.common.utils.Result;
import com.codermy.myspringsecurityplus.common.utils.ResultCode;
import com.codermy.myspringsecurityplus.common.utils.SecurityUtils;
import com.codermy.myspringsecurityplus.common.utils.UserConstants;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DictServiceImpl implements DictService {
    @Autowired
    private DictDao dictDao;

    @Autowired
    private DictDetailDao dictDetailDao;
    @Override
    public Result<SysDict> getDictPage(Integer offectPosition, Integer limit, SysDict sysDict) {
        Page page = PageHelper.offsetPage(offectPosition,limit);
        List<SysDict> fuzzyDictByPage = dictDao.getFuzzyDictByPage(sysDict);
        return Result.ok().count(page.getTotal()).data(fuzzyDictByPage).code(ResultCode.TABLE_SUCCESS);
    }

    @Override
    public SysDict getDictByName(String dictName) {
        return dictDao.getDictByName(dictName);
    }

    @Override
    public String checkDictNameUnique(SysDict sysDict) {
        SysDict info = dictDao.getDictByName(sysDict.getDictName());
        if (ObjectUtil.isNotEmpty(info) && !info.getDictId().equals (sysDict.getDictId())){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public int insertDict(SysDict sysDict) {
        return dictDao.insert(sysDict);
    }

    @Override
    public SysDict getDictById(Integer dictId) {
        return dictDao.getDictById(dictId);
    }

    @Override
    public int updateDict(SysDict sysDict) {
        sysDict.setUpdateBy(SecurityUtils.getCurrentUsername());
        sysDict.setUpdateTime(new Date());
        return dictDao.updateDict(sysDict);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int deleteDictByIds(String ids)  throws MyException {
        Integer[] dictIds = Convert.toIntArray(ids);
        for (Integer dictId : dictIds){;
            dictDetailDao.deleteDictDetailByDictId(dictId);;
        }
        return dictDao.deleteDictByIds(dictIds);
    }
}
