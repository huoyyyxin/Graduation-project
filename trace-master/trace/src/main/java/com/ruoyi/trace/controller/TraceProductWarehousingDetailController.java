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
import com.ruoyi.trace.domain.TraceProductWarehousingDetail;
import com.ruoyi.trace.service.ITraceProductWarehousingDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 生产入库明细Controller
 * 
 * @author ruoyi
 * @date 2024-02-24
 */
@RestController
@RequestMapping("/warehousing/detail")
public class TraceProductWarehousingDetailController extends BaseController
{
    @Autowired
    private ITraceProductWarehousingDetailService traceProductWarehousingDetailService;

    @Autowired
    private ITraceProductionWarehousingService traceProductionWarehousingService;
    /**
     * 查询生产入库明细列表
     */
    @PreAuthorize("@ss.hasPermi('warehousing:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(TraceProductWarehousingDetail traceProductWarehousingDetail)
    {
        startPage();
        List<TraceProductWarehousingDetail> list = traceProductWarehousingDetailService.selectTraceProductWarehousingDetailList(traceProductWarehousingDetail);
        return getDataTable(list);
    }
    /**
     * 查询所有生产入库明细列表
     */
    @PreAuthorize("@ss.hasPermi('warehousing:detail:list')")
    @GetMapping("/listAll")
    public TableDataInfo listAll( )
    {
        TraceProductWarehousingDetail traceProductWarehousingDetail = new TraceProductWarehousingDetail();
        List<TraceProductWarehousingDetail> list = traceProductWarehousingDetailService.selectTraceProductWarehousingDetailList(traceProductWarehousingDetail);
        return getDataTable(list);
    }

    /**
     * 导出生产入库明细列表
     */
    @PreAuthorize("@ss.hasPermi('warehousing:detail:export')")
    @Log(title = "生产入库明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TraceProductWarehousingDetail traceProductWarehousingDetail)
    {
        List<TraceProductWarehousingDetail> list = traceProductWarehousingDetailService.selectTraceProductWarehousingDetailList(traceProductWarehousingDetail);
        ExcelUtil<TraceProductWarehousingDetail> util = new ExcelUtil<TraceProductWarehousingDetail>(TraceProductWarehousingDetail.class);
        util.exportExcel(response, list, "生产入库明细数据");
    }

    /**
     * 获取生产入库明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('warehousing:detail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(traceProductWarehousingDetailService.selectTraceProductWarehousingDetailById(id));
    }

    /**
     * 新增生产入库明细
     */
    @PreAuthorize("@ss.hasPermi('warehousing:detail:add')")
    @Log(title = "生产入库明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TraceProductWarehousingDetail traceProductWarehousingDetail)
    {
        return toAjax(traceProductWarehousingDetailService.insertTraceProductWarehousingDetail(traceProductWarehousingDetail));
    }

    /**
     * 修改生产入库明细
     */
    @PreAuthorize("@ss.hasPermi('warehousing:detail:edit')")
    @Log(title = "生产入库明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TraceProductWarehousingDetail traceProductWarehousingDetail)
    {
        return toAjax(traceProductWarehousingDetailService.updateTraceProductWarehousingDetail(traceProductWarehousingDetail));
    }

    /**
     * 删除生产入库明细
     */
    @PreAuthorize("@ss.hasPermi('warehousing:detail:remove')")
    @Log(title = "生产入库明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(traceProductWarehousingDetailService.deleteTraceProductWarehousingDetailByIds(ids));
    }
}
