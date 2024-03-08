package com.ruoyi.trace.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.trace.mapper.DepartmentMapper;
import com.ruoyi.trace.domain.Department;
import com.ruoyi.trace.service.IDepartmentService;

/**
 * departmentService业务层处理
 * 
 * @author zhang
 * @date 2024-01-26
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService 
{
    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 查询department
     * 
     * @param departmentId department主键
     * @return department
     */
    @Override
    public Department selectDepartmentByDepartmentId(Long departmentId)
    {
        return departmentMapper.selectDepartmentByDepartmentId(departmentId);
    }

    /**
     * 查询department列表
     * 
     * @param department department
     * @return department
     */
    @Override
    public List<Department> selectDepartmentList(Department department)
    {
        return departmentMapper.selectDepartmentList(department);
    }

    /**
     * 新增department
     * 
     * @param department department
     * @return 结果
     */
    @Override
    public int insertDepartment(Department department)
    {
        department.setCreateTime(DateUtils.getNowDate());
        return departmentMapper.insertDepartment(department);
    }

    /**
     * 修改department
     * 
     * @param department department
     * @return 结果
     */
    @Override
    public int updateDepartment(Department department)
    {
        department.setUpdateTime(DateUtils.getNowDate());
        return departmentMapper.updateDepartment(department);
    }

    /**
     * 批量删除department
     * 
     * @param departmentIds 需要删除的department主键
     * @return 结果
     */
    @Override
    public int deleteDepartmentByDepartmentIds(Long[] departmentIds)
    {
        return departmentMapper.deleteDepartmentByDepartmentIds(departmentIds);
    }

    /**
     * 删除department信息
     * 
     * @param departmentId department主键
     * @return 结果
     */
    @Override
    public int deleteDepartmentByDepartmentId(Long departmentId)
    {
        return departmentMapper.deleteDepartmentByDepartmentId(departmentId);
    }
}
