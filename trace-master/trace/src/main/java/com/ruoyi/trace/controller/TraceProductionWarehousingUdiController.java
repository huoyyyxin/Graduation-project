package com.ruoyi.trace.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.trace.service.ITraceProductionWarehousingService;
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
import com.ruoyi.trace.domain.TraceProductionWarehousingUdi;
import com.ruoyi.trace.service.ITraceProductionWarehousingUdiService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 生产入库UDI明细Controller
 * 
 * @author ruoyi
 * @date 2024-02-26
 */
@RestController
@RequestMapping("/warehousing/udi")
public class TraceProductionWarehousingUdiController extends BaseController
{
    @Autowired
    private ITraceProductionWarehousingUdiService traceProductionWarehousingUdiService;

    @Autowired
    private ITraceProductionWarehousingService traceProductionWarehousingService;

    /**
     * 查询生产入库UDI明细列表
     */
    @PreAuthorize("@ss.hasPermi('warehousing:udi:list')")
    @GetMapping("/list")
    public TableDataInfo list(TraceProductionWarehousingUdi traceProductionWarehousingUdi)
    {
        startPage();
        List<TraceProductionWarehousingUdi> list = traceProductionWarehousingUdiService.selectTraceProductionWarehousingUdiList(traceProductionWarehousingUdi);
        return getDataTable(list);
    }

    /**
     * 导出生产入库UDI明细列表
     */
    @PreAuthorize("@ss.hasPermi('warehousing:udi:export')")
    @Log(title = "生产入库UDI明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TraceProductionWarehousingUdi traceProductionWarehousingUdi)
    {
        List<TraceProductionWarehousingUdi> list = traceProductionWarehousingUdiService.selectTraceProductionWarehousingUdiList(traceProductionWarehousingUdi);
        ExcelUtil<TraceProductionWarehousingUdi> util = new ExcelUtil<TraceProductionWarehousingUdi>(TraceProductionWarehousingUdi.class);
        util.exportExcel(response, list, "生产入库UDI明细数据");
    }

    /**
     * 获取生产入库UDI明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('warehousing:udi:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(traceProductionWarehousingUdiService.selectTraceProductionWarehousingUdiById(id));
    }

    /**
     * 新增生产入库UDI明细
     */
    @PreAuthorize("@ss.hasPermi('warehousing:udi:add')")
    @Log(title = "生产入库UDI明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TraceProductionWarehousingUdi traceProductionWarehousingUdi)
    {
        return toAjax(traceProductionWarehousingUdiService.insertTraceProductionWarehousingUdi(traceProductionWarehousingUdi));
    }

    /**
     * 修改生产入库UDI明细
     */
    @PreAuthorize("@ss.hasPermi('warehousing:udi:edit')")
    @Log(title = "生产入库UDI明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TraceProductionWarehousingUdi traceProductionWarehousingUdi)
    {
        return toAjax(traceProductionWarehousingUdiService.updateTraceProductionWarehousingUdi(traceProductionWarehousingUdi));
    }

    /**
     * 删除生产入库UDI明细
     */
    @PreAuthorize("@ss.hasPermi('warehousing:udi:remove')")
    @Log(title = "生产入库UDI明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(traceProductionWarehousingUdiService.deleteTraceProductionWarehousingUdiByIds(ids));
    }
}
