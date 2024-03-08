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
import com.ruoyi.trace.domain.SystemParameter;
import com.ruoyi.trace.service.ISystemParameterService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * parameterController
 * 
 * @author zhang
 * @date 2024-01-26
 */
@RestController
@RequestMapping("/trace/parameter")
public class SystemParameterController extends BaseController
{
    @Autowired
    private ISystemParameterService systemParameterService;

    /**
     * 查询parameter列表
     */
    @PreAuthorize("@ss.hasPermi('trace:parameter:list')")
    @GetMapping("/list")
    public TableDataInfo list(SystemParameter systemParameter)
    {
        startPage();
        List<SystemParameter> list = systemParameterService.selectSystemParameterList(systemParameter);
        return getDataTable(list);
    }

    /**
     * 导出parameter列表
     */
    @PreAuthorize("@ss.hasPermi('trace:parameter:export')")
    @Log(title = "parameter", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SystemParameter systemParameter)
    {
        List<SystemParameter> list = systemParameterService.selectSystemParameterList(systemParameter);
        ExcelUtil<SystemParameter> util = new ExcelUtil<SystemParameter>(SystemParameter.class);
        util.exportExcel(response, list, "parameter数据");
    }

    /**
     * 获取parameter详细信息
     */
    @PreAuthorize("@ss.hasPermi('trace:parameter:query')")
    @GetMapping(value = "/{systemParameterId}")
    public AjaxResult getInfo(@PathVariable("systemParameterId") Long systemParameterId)
    {
        return success(systemParameterService.selectSystemParameterBySystemParameterId(systemParameterId));
    }

    /**
     * 新增parameter
     */
    @PreAuthorize("@ss.hasPermi('trace:parameter:add')")
    @Log(title = "parameter", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SystemParameter systemParameter)
    {
        return toAjax(systemParameterService.insertSystemParameter(systemParameter));
    }

    /**
     * 修改parameter
     */
    @PreAuthorize("@ss.hasPermi('trace:parameter:edit')")
    @Log(title = "parameter", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SystemParameter systemParameter)
    {
        return toAjax(systemParameterService.updateSystemParameter(systemParameter));
    }

    /**
     * 删除parameter
     */
    @PreAuthorize("@ss.hasPermi('trace:parameter:remove')")
    @Log(title = "parameter", businessType = BusinessType.DELETE)
	@DeleteMapping("/{systemParameterIds}")
    public AjaxResult remove(@PathVariable Long[] systemParameterIds)
    {
        return toAjax(systemParameterService.deleteSystemParameterBySystemParameterIds(systemParameterIds));
    }
}
