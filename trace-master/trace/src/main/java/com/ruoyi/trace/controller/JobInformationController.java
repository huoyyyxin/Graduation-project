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
import com.ruoyi.trace.domain.JobInformation;
import com.ruoyi.trace.service.IJobInformationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * jobController
 * 
 * @author zhang
 * @date 2024-01-26
 */
@RestController
@RequestMapping("/trace/job")
public class JobInformationController extends BaseController
{
    @Autowired
    private IJobInformationService jobInformationService;

    /**
     * 查询job列表
     */
    @PreAuthorize("@ss.hasPermi('trace:job:list')")
    @GetMapping("/list")
    public TableDataInfo list(JobInformation jobInformation)
    {
        startPage();
        List<JobInformation> list = jobInformationService.selectJobInformationList(jobInformation);
        return getDataTable(list);
    }

    /**
     * 导出job列表
     */
    @PreAuthorize("@ss.hasPermi('trace:job:export')")
    @Log(title = "job", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JobInformation jobInformation)
    {
        List<JobInformation> list = jobInformationService.selectJobInformationList(jobInformation);
        ExcelUtil<JobInformation> util = new ExcelUtil<JobInformation>(JobInformation.class);
        util.exportExcel(response, list, "job数据");
    }

    /**
     * 获取job详细信息
     */
    @PreAuthorize("@ss.hasPermi('trace:job:query')")
    @GetMapping(value = "/{jobId}")
    public AjaxResult getInfo(@PathVariable("jobId") Long jobId)
    {
        return success(jobInformationService.selectJobInformationByJobId(jobId));
    }

    /**
     * 新增job
     */
    @PreAuthorize("@ss.hasPermi('trace:job:add')")
    @Log(title = "job", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JobInformation jobInformation)
    {
        return toAjax(jobInformationService.insertJobInformation(jobInformation));
    }

    /**
     * 修改job
     */
    @PreAuthorize("@ss.hasPermi('trace:job:edit')")
    @Log(title = "job", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JobInformation jobInformation)
    {
        return toAjax(jobInformationService.updateJobInformation(jobInformation));
    }

    /**
     * 删除job
     */
    @PreAuthorize("@ss.hasPermi('trace:job:remove')")
    @Log(title = "job", businessType = BusinessType.DELETE)
	@DeleteMapping("/{jobIds}")
    public AjaxResult remove(@PathVariable Long[] jobIds)
    {
        return toAjax(jobInformationService.deleteJobInformationByJobIds(jobIds));
    }
}
