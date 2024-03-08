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
import com.ruoyi.trace.domain.PersonnelInformation;
import com.ruoyi.trace.service.IPersonnelInformationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * personnel_informationController
 * 
 * @author zhang
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/trace/personnel_information")
public class PersonnelInformationController extends BaseController
{
    @Autowired
    private IPersonnelInformationService personnelInformationService;

    /**
     * 查询personnel_information列表
     */
    @PreAuthorize("@ss.hasPermi('trace:personnel_information:list')")
    @GetMapping("/list")
    public TableDataInfo list(PersonnelInformation personnelInformation)
    {
        startPage();
        List<PersonnelInformation> list = personnelInformationService.selectPersonnelInformationList(personnelInformation);
        return getDataTable(list);
    }

    /**
     * 导出personnel_information列表
     */
    @PreAuthorize("@ss.hasPermi('trace:personnel_information:export')")
    @Log(title = "personnel_information", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PersonnelInformation personnelInformation)
    {
        List<PersonnelInformation> list = personnelInformationService.selectPersonnelInformationList(personnelInformation);
        ExcelUtil<PersonnelInformation> util = new ExcelUtil<PersonnelInformation>(PersonnelInformation.class);
        util.exportExcel(response, list, "personnel_information数据");
    }

    /**
     * 获取personnel_information详细信息
     */
    @PreAuthorize("@ss.hasPermi('trace:personnel_information:query')")
    @GetMapping(value = "/{personnelInformationId}")
    public AjaxResult getInfo(@PathVariable("personnelInformationId") String personnelInformationId)
    {
        return success(personnelInformationService.selectPersonnelInformationByPersonnelInformationId(personnelInformationId));
    }

    /**
     * 新增personnel_information
     */
    @PreAuthorize("@ss.hasPermi('trace:personnel_information:add')")
    @Log(title = "personnel_information", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PersonnelInformation personnelInformation)
    {
        return toAjax(personnelInformationService.insertPersonnelInformation(personnelInformation));
    }

    /**
     * 修改personnel_information
     */
    @PreAuthorize("@ss.hasPermi('trace:personnel_information:edit')")
    @Log(title = "personnel_information", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PersonnelInformation personnelInformation)
    {
        return toAjax(personnelInformationService.updatePersonnelInformation(personnelInformation));
    }

    /**
     * 删除personnel_information
     */
    @PreAuthorize("@ss.hasPermi('trace:personnel_information:remove')")
    @Log(title = "personnel_information", businessType = BusinessType.DELETE)
	@DeleteMapping("/{personnelInformationIds}")
    public AjaxResult remove(@PathVariable String[] personnelInformationIds)
    {
        return toAjax(personnelInformationService.deletePersonnelInformationByPersonnelInformationIds(personnelInformationIds));
    }
}
