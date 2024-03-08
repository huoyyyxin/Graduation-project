package com.ruoyi.trace.controller;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.trace.domain.TraceProductWarehousingDetail;
import com.ruoyi.trace.domain.TraceProductionWarehousingUdi;
import com.ruoyi.trace.service.ITraceProductWarehousingDetailService;
import com.ruoyi.trace.service.ITraceProductionWarehousingUdiService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.trace.domain.TraceProductionWarehousing;
import com.ruoyi.trace.service.ITraceProductionWarehousingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 生产入库Controller
 *
 * @author ruoyi
 * @date 2024-02-26
 */
@RestController
@RequestMapping("/warehousing/warehousing")
public class TraceProductionWarehousingController extends BaseController {
    @Autowired
    private ITraceProductionWarehousingService traceProductionWarehousingService;
    @Autowired
    private ITraceProductWarehousingDetailService traceProductWarehousingDetailService;
    @Autowired
    private ITraceProductionWarehousingUdiService traceProductionWarehousingUdiService;

    /**
     * 查询生产入库列表
     */
    @PreAuthorize("@ss.hasPermi('warehousing:warehousing:list')")
    @GetMapping("/list")
    public TableDataInfo list(TraceProductionWarehousing traceProductionWarehousing) {
        startPage();
        List<TraceProductionWarehousing> list = traceProductionWarehousingService.selectTraceProductionWarehousingList(traceProductionWarehousing);
        return getDataTable(list);
    }

    /**
     * 查询生产入库表编码
     */
    @PreAuthorize("@ss.hasPermi('warehousing:warehousing:list')")
    @GetMapping("/listCode")
    public TableDataInfo listCode() {
        TraceProductionWarehousing traceProductionWarehousing = new TraceProductionWarehousing();
        List<TraceProductionWarehousing> list = traceProductionWarehousingService.selectTraceProductionWarehousingList(traceProductionWarehousing);
        return getDataTable(list);
    }
    /**
     * 导出生产入库列表
     */
    @PreAuthorize("@ss.hasPermi('warehousing:warehousing:export')")
    @Log(title = "生产入库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TraceProductionWarehousing traceProductionWarehousing) {
        List<TraceProductionWarehousing> list = traceProductionWarehousingService.selectTraceProductionWarehousingList(traceProductionWarehousing);
        ExcelUtil<TraceProductionWarehousing> util = new ExcelUtil<TraceProductionWarehousing>(TraceProductionWarehousing.class);
        util.exportExcel(response, list, "生产入库数据");
    }

    /**
     * 获取生产入库详细信息
     */
    @PreAuthorize("@ss.hasPermi('warehousing:warehousing:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(traceProductionWarehousingService.selectTraceProductionWarehousingById(id));
    }

    /**
     * 新增生产入库
     */
    @PreAuthorize("@ss.hasPermi('warehousing:warehousing:add')")
    @Log(title = "生产入库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TraceProductionWarehousing traceProductionWarehousing) {
        TraceProductionWarehousing query = new TraceProductionWarehousing();
        query.setProductionWarehousingCode(traceProductionWarehousing.getProductionWarehousingCode());
        if (traceProductionWarehousingService.selectTraceProductionWarehousingListEqual(query).size() > 0) {
            return AjaxResult.error("入库编号已存在，不能重复添加");
        }

        traceProductionWarehousing.setCreateBy(getUsername());
        return toAjax(traceProductionWarehousingService.insertTraceProductionWarehousing(traceProductionWarehousing));
    }

    /**
     * 修改生产入库
     */
    @PreAuthorize("@ss.hasPermi('warehousing:warehousing:edit')")
    @Log(title = "生产入库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TraceProductionWarehousing traceProductionWarehousing) {
        traceProductionWarehousing.setUpdateBy(getUsername());
        return toAjax(traceProductionWarehousingService.updateTraceProductionWarehousing(traceProductionWarehousing));
    }

    /**
     * 删除生产入库
     */
    @PreAuthorize("@ss.hasPermi('warehousing:warehousing:remove')")
    @Log(title = "生产入库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult remove(@PathVariable Long[] ids) {
        String[] tpwid = new String[ids.length];
        for (int i=0;i<ids.length;i++){
            tpwid[i]=traceProductionWarehousingService.selectTraceProductionWarehousingById(ids[i]).getProductionWarehousingCode();
        }
        traceProductionWarehousingUdiService.deleteTraceProductionWarehousingByTpwid(tpwid);
        traceProductWarehousingDetailService.deleteTraceProductionWarehousingDetailByTpwid(tpwid);
    return toAjax(traceProductionWarehousingService.deleteTraceProductionWarehousingByIds(ids));
    }

    /**
     * 根据code查询detail和udi
     */
    @PostMapping("/selectWarehouseFromCode")
    public AjaxResult selectWarehouseFromCode(@RequestParam("code") String code) {
        TraceProductWarehousingDetail traceProductWarehousingDetail = traceProductWarehousingDetailService.selectDetailWithCode(code);
        List<TraceProductionWarehousingUdi> traceProductionWarehousingUdiList = traceProductionWarehousingUdiService.selectUdiWithCode(code);
        HashMap<String, Object> AMap = new HashMap<>();
        AMap.put("detail", traceProductWarehousingDetail);
        AMap.put("Udi", traceProductionWarehousingUdiList);
        return AjaxResult.success(AMap);
    }


}
