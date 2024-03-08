package com.ruoyi.trace.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.trace.domain.Department;
import com.ruoyi.trace.service.IDepartmentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * departmentController
 * 
 * @author zhang
 * @date 2024-01-26
 */
@RestController
@RequestMapping("/trace/department")
public class DepartmentController extends BaseController
{
    @Autowired
    private IDepartmentService departmentService;

    /**
     * 查询department列表
     */
    @PreAuthorize("@ss.hasPermi('trace:department:list')")
    @GetMapping("/list")
    public TableDataInfo list(Department department)
    {
        startPage();
        List<Department> list = departmentService.selectDepartmentList(department);
        return getDataTable(list);
    }

    /**
     * 导出department列表
     */
    @PreAuthorize("@ss.hasPermi('trace:department:export')")
    @Log(title = "department", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Department department)
    {
        List<Department> list = departmentService.selectDepartmentList(department);
        ExcelUtil<Department> util = new ExcelUtil<Department>(Department.class);
        util.exportExcel(response, list, "department数据");
    }

    /**
     * 获取department详细信息
     */
    @PreAuthorize("@ss.hasPermi('trace:department:query')")
    @GetMapping(value = "/{departmentId}")
    public AjaxResult getInfo(@PathVariable("departmentId") Long departmentId)
    {
        return success(departmentService.selectDepartmentByDepartmentId(departmentId));
    }

    /**
     * 新增department
     */
    @PreAuthorize("@ss.hasPermi('trace:department:add')")
    @Log(title = "department", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Department department)
    {
        return toAjax(departmentService.insertDepartment(department));
    }

    /**
     * 修改department
     */
    @PreAuthorize("@ss.hasPermi('trace:department:edit')")
    @Log(title = "department", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Department department)
    {
        return toAjax(departmentService.updateDepartment(department));
    }

    /**
     * 删除department
     */
    @PreAuthorize("@ss.hasPermi('trace:department:remove')")
    @Log(title = "department", businessType = BusinessType.DELETE)
	@DeleteMapping("/{departmentIds}")
    public AjaxResult remove(@PathVariable Long[] departmentIds)
    {
        return toAjax(departmentService.deleteDepartmentByDepartmentIds(departmentIds));
    }
}
