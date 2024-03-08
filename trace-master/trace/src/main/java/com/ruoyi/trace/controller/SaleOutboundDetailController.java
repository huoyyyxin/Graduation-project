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
import com.ruoyi.trace.domain.SaleOutboundDetail;
import com.ruoyi.trace.service.ISaleOutboundDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 销售出库明细Controller
 * 
 * @author zhang
 * @date 2024-03-08
 */
@RestController
@RequestMapping("/trace/outboundDetail")
public class SaleOutboundDetailController extends BaseController
{
    @Autowired
    private ISaleOutboundDetailService saleOutboundDetailService;

    /**
     * 查询销售出库明细列表
     */
    @PreAuthorize("@ss.hasPermi('trace:outboundDetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(SaleOutboundDetail saleOutboundDetail)
    {
        startPage();
        List<SaleOutboundDetail> list = saleOutboundDetailService.selectSaleOutboundDetailList(saleOutboundDetail);
        return getDataTable(list);
    }

    /**
     * 导出销售出库明细列表
     */
    @PreAuthorize("@ss.hasPermi('trace:outboundDetail:export')")
    @Log(title = "销售出库明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SaleOutboundDetail saleOutboundDetail)
    {
        List<SaleOutboundDetail> list = saleOutboundDetailService.selectSaleOutboundDetailList(saleOutboundDetail);
        ExcelUtil<SaleOutboundDetail> util = new ExcelUtil<SaleOutboundDetail>(SaleOutboundDetail.class);
        util.exportExcel(response, list, "销售出库明细数据");
    }

    /**
     * 获取销售出库明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('trace:outboundDetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(saleOutboundDetailService.selectSaleOutboundDetailById(id));
    }

    /**
     * 新增销售出库明细
     */
    @PreAuthorize("@ss.hasPermi('trace:outboundDetail:add')")
    @Log(title = "销售出库明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SaleOutboundDetail saleOutboundDetail)
    {
        return toAjax(saleOutboundDetailService.insertSaleOutboundDetail(saleOutboundDetail));
    }

    /**
     * 修改销售出库明细
     */
    @PreAuthorize("@ss.hasPermi('trace:outboundDetail:edit')")
    @Log(title = "销售出库明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SaleOutboundDetail saleOutboundDetail)
    {
        return toAjax(saleOutboundDetailService.updateSaleOutboundDetail(saleOutboundDetail));
    }

    /**
     * 删除销售出库明细
     */
    @PreAuthorize("@ss.hasPermi('trace:outboundDetail:remove')")
    @Log(title = "销售出库明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(saleOutboundDetailService.deleteSaleOutboundDetailByIds(ids));
    }
}
