package com.codermy.myspringsecurityplus.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.codermy.myspringsecurityplus.admin.annotation.DataPermission;
import com.codermy.myspringsecurityplus.admin.dao.DeptDao;
import com.codermy.myspringsecurityplus.admin.dto.DeptDto;
import com.codermy.myspringsecurityplus.admin.entity.SysDept;
import com.codermy.myspringsecurityplus.admin.service.DeptService;
import com.codermy.myspringsecurityplus.common.exceptionhandler.MyException;
import com.codermy.myspringsecurityplus.common.utils.ResultCode;
import com.codermy.myspringsecurityplus.common.utils.TreeUtil;
import com.codermy.myspringsecurityplus.common.utils.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/8/19
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;

    @Override
    @DataPermission(deptAlias = "d")
    public List<SysDept> getDeptAll(SysDept sysDept) {
        return deptDao.getFuzzyDept(sysDept);
    }

    @Override
    @DataPermission(deptAlias = "d")
    public List<DeptDto> buildDeptAll(DeptDto deptDto) {
        return deptDao.buildAll(deptDto);
    }

    @Override
    @DataPermission(deptAlias = "d")
    public List<DeptDto> roleDeptTreeData(Integer roleId) {
        List<DeptDto> selectRoleDeptTree = deptDao.selectRoleDeptTree(roleId);
        DeptDto deptDto = new DeptDto();
        List<DeptDto> buildAll = deptDao.buildAll(deptDto);
        List<DeptDto> tree = TreeUtil.deptTree(selectRoleDeptTree, buildAll);
        return tree;
    }

    @Override
    public int insertDept(SysDept dept) {
        SysDept info = deptDao.selectDeptById(dept.getParentId());
        if (UserConstants.DEPT_DISABLE.equals(info.getStatus().toString()))
        {
            throw new MyException(ResultCode.ERROR,"部门停用，不允许新增");
        }
        dept.setAncestors(info.getAncestors() + "," + dept.getParentId());
        return deptDao.insertDept(dept);
    }

    @Override
    public String checkDeptNameUnique(SysDept dept) {
        SysDept info = deptDao.checkDeptNameUnique(dept.getDeptName(),dept.getParentId());
        if (ObjectUtil.isNotEmpty(info) && !info.getDeptId().equals(dept.getDeptId())){
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;

    }

    @Override
    public SysDept selectDeptById(Integer deptId) {
        return deptDao.selectDeptById(deptId);
    }

    @Override
    public SysDept getDeptById(Integer deptId) {
        return deptDao.getDeptById(deptId);
    }

    @Override
    public int updateDept(SysDept dept) {
        SysDept parentInfo = deptDao.selectDeptById(dept.getParentId());
        SysDept oldInfo = selectDeptById(dept.getDeptId());
        if(ObjectUtil.isNotEmpty(parentInfo) &&ObjectUtil.isNotEmpty(oldInfo)){
            String newAncestors = parentInfo.getAncestors() + "," + parentInfo.getDeptId();
            String oldAncestors = oldInfo.getAncestors();
            dept.setAncestors(newAncestors);
            updateDeptChildren(dept.getDeptId(), newAncestors, oldAncestors);
        }
        int result =deptDao.updateDept(dept);
        if (UserConstants.DEPT_NORMAL.equals(dept.getStatus().toString()))
        {
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentDeptStatus(dept);
        }
        return result;
    }

    @Override
    public int selectNormalChildrenDeptById(Integer deptId) {
        return deptDao.selectNormalChildrenDeptById(deptId);
    }

    @Override
    public int selectDeptCount(Integer parentId) {
        SysDept dept =new SysDept();
        dept.setParentId(parentId);
        return deptDao.selectDeptCount(dept);
    }

    @Override
    public boolean checkDeptExistUser(Integer deptId) {
        int result = deptDao.checkDeptExistUser(deptId);
        return result > 0 ? true : false;
    }

    @Override
    public int deleteDeptById(Integer deptId) {
        return deptDao.deleteDeptById(deptId);
    }

    @Override
    public int changeStatus(SysDept sysDept) {
        return deptDao.updateDept(sysDept);
    }

    /**
     * 修改子元素关系
     *
     * @param id 被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateDeptChildren(Integer id, String newAncestors, String oldAncestors)
    {
        List<SysDept> children = deptDao.selectChildrenDeptById(id);
        for (SysDept child : children)
        {
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            deptDao.updateDeptChildren(children);
        }
    }

    /**
     * 修改该部门的父级部门状态
     *
     * @param dept 当前部门
     */
    private void updateParentDeptStatus(SysDept dept)
    {
        dept = deptDao.selectDeptById(dept.getDeptId());;
        deptDao.updateDeptStatus(dept);
    }
}
