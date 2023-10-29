package com.codermy.myspringsecurityplus.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codermy.myspringsecurityplus.admin.dto.DeptDto;
import com.codermy.myspringsecurityplus.admin.entity.SysDept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/8/19
 * @modifiedBy CodePoorFish
 * @updateTime 2013/10/12
 */
@Mapper
public interface DeptDao extends BaseMapper<SysDept> {
    /**
     * 模糊查询部门
     * @param sysDept 查询的名称
     * @return
     */
    List<SysDept> getFuzzyDept(SysDept sysDept);


    /**
     * 部门树
     * @param deptDto
     * @return
     */
    List<DeptDto> buildAll(DeptDto deptDto);

    /**
     * 校验部门名称
     * @param deptName 岗位名称
     * @param parentId
     * @return
     */
    SysDept checkDeptNameUnique(@Param("deptName")String deptName, @Param("parentId") Integer parentId);


//    /**
//     * 新增部门信息
//     * @param dept 岗位信息
//     * @return 结果
//     */
//    @Insert("INSERT INTO sys_dept(parent_id,ancestors,dept_name,sort,status,enabled) values(#{parentId},#{ancestors},#{deptName},#{sort},#{status},#{createBy},#{enabled})")
//    int insertDept(SysDept dept);

    /**
     * 根据部门ID查询信息
     * @param deptId 部门ID
     * @return 部门信息
     */
    SysDept selectDeptById(Integer deptId);

    /**
     * 通过id查询部门信息
     * @param deptId
     * @return
     */
    @Select("select d.dept_id,d.parent_id,d.ancestors,d.dept_name,d.sort,d.status,d.create_by,d.create_time,d.update_by,d.update_time,d.enabled from sys_dept d where d.dept_id = #{deptId}")
    SysDept getDeptById(Integer deptId);


    /**
     * 根据ID查询所有子部门
     *
     * @param id 部门ID
     * @return 部门列表
     */
     List<SysDept> selectChildrenDeptById(Integer id);


    /**
     * 根据角色ID查询部门
     *
     * @param id 角色ID
     * @return 部门列表
     */
     List<DeptDto> selectRoleDeptTree(Integer id);
    /**
     * 修改子元素关系
     *
     * @param depts 子元素
     * @return 结果
     */
    int updateDeptChildren(@Param("depts")List<SysDept> depts);

    /**
     * 修改部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    int updateDept(SysDept dept);

    /**
     * 修改所在部门的父级部门状态
     *
     * @param dept 部门
     */
     void updateDeptStatus(SysDept dept);

    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param deptId 部门ID
     * @return 子部门数
     */
    int selectNormalChildrenDeptById(Integer deptId);
    /**
     * 查询部门人数
     *
     * @param dept 部门信息
     * @return 结果
     */
    int selectDeptCount(SysDept dept);

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果
     */
    int checkDeptExistUser(Integer deptId);

    /**
     * 根据部门ID删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    int deleteDeptById(Integer deptId);
}
