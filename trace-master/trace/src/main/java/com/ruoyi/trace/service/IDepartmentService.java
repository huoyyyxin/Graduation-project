package com.ruoyi.trace.service;

import java.util.List;
import com.ruoyi.trace.domain.Department;

/**
 * departmentService接口
 * 
 * @author zhang
 * @date 2024-01-26
 */
public interface IDepartmentService 
{
    /**
     * 查询department
     * 
     * @param departmentId department主键
     * @return department
     */
    public Department selectDepartmentByDepartmentId(Long departmentId);

    /**
     * 查询department列表
     * 
     * @param department department
     * @return department集合
     */
    public List<Department> selectDepartmentList(Department department);

    /**
     * 新增department
     * 
     * @param department department
     * @return 结果
     */
    public int insertDepartment(Department department);

    /**
     * 修改department
     * 
     * @param department department
     * @return 结果
     */
    public int updateDepartment(Department department);

    /**
     * 批量删除department
     * 
     * @param departmentIds 需要删除的department主键集合
     * @return 结果
     */
    public int deleteDepartmentByDepartmentIds(Long[] departmentIds);

    /**
     * 删除department信息
     * 
     * @param departmentId department主键
     * @return 结果
     */
    public int deleteDepartmentByDepartmentId(Long departmentId);
}
