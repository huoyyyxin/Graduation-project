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
import com.ruoyi.trace.domain.SaleOutbound;
import com.ruoyi.trace.service.ISaleOutboundService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 销售出库Controller
 * 
 * @author zhang
 * @date 2024-03-08
 */
@RestController
@RequestMapping("/trace/outbound")
public class SaleOutboundController extends BaseController
{
    @Autowired
    private ISaleOutboundService saleOutboundService;

    /**
     * 查询销售出库列表
     */
    @PreAuthorize("@ss.hasPermi('trace:outbound:list')")
    @GetMapping("/list")
    public TableDataInfo list(SaleOutbound saleOutbound)
    {
        startPage();
        List<SaleOutbound> list = saleOutboundService.selectSaleOutboundList(saleOutbound);
        return getDataTable(list);
    }

    /**
     * 导出销售出库列表
     */
    @PreAuthorize("@ss.hasPermi('trace:outbound:export')")
    @Log(title = "销售出库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SaleOutbound saleOutbound)
    {
        List<SaleOutbound> list = saleOutboundService.selectSaleOutboundList(saleOutbound);
        ExcelUtil<SaleOutbound> util = new ExcelUtil<SaleOutbound>(SaleOutbound.class);
        util.exportExcel(response, list, "销售出库数据");
    }

    /**
     * 获取销售出库详细信息
     */
    @PreAuthorize("@ss.hasPermi('trace:outbound:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(saleOutboundService.selectSaleOutboundById(id));
    }

    /**
     * 新增销售出库
     */
    @PreAuthorize("@ss.hasPermi('trace:outbound:add')")
    @Log(title = "销售出库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SaleOutbound saleOutbound)
    {
        return toAjax(saleOutboundService.insertSaleOutbound(saleOutbound));
    }

    /**
     * 修改销售出库
     */
    @PreAuthorize("@ss.hasPermi('trace:outbound:edit')")
    @Log(title = "销售出库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SaleOutbound saleOutbound)
    {
        return toAjax(saleOutboundService.updateSaleOutbound(saleOutbound));
    }

    /**
     * 删除销售出库
     */
    @PreAuthorize("@ss.hasPermi('trace:outbound:remove')")
    @Log(title = "销售出库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(saleOutboundService.deleteSaleOutboundByIds(ids));
    }
}
