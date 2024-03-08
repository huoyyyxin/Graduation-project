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
import com.ruoyi.trace.domain.SaleOutboundUdi;
import com.ruoyi.trace.service.ISaleOutboundUdiService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 销售出库UDI信息Controller
 * 
 * @author zhang
 * @date 2024-03-08
 */
@RestController
@RequestMapping("/trace/outboundUDI")
public class SaleOutboundUdiController extends BaseController
{
    @Autowired
    private ISaleOutboundUdiService saleOutboundUdiService;

    /**
     * 查询销售出库UDI信息列表
     */
    @PreAuthorize("@ss.hasPermi('trace:outboundUDI:list')")
    @GetMapping("/list")
    public TableDataInfo list(SaleOutboundUdi saleOutboundUdi)
    {
        startPage();
        List<SaleOutboundUdi> list = saleOutboundUdiService.selectSaleOutboundUdiList(saleOutboundUdi);
        return getDataTable(list);
    }

    /**
     * 导出销售出库UDI信息列表
     */
    @PreAuthorize("@ss.hasPermi('trace:outboundUDI:export')")
    @Log(title = "销售出库UDI信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SaleOutboundUdi saleOutboundUdi)
    {
        List<SaleOutboundUdi> list = saleOutboundUdiService.selectSaleOutboundUdiList(saleOutboundUdi);
        ExcelUtil<SaleOutboundUdi> util = new ExcelUtil<SaleOutboundUdi>(SaleOutboundUdi.class);
        util.exportExcel(response, list, "销售出库UDI信息数据");
    }

    /**
     * 获取销售出库UDI信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('trace:outboundUDI:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(saleOutboundUdiService.selectSaleOutboundUdiById(id));
    }

    /**
     * 新增销售出库UDI信息
     */
    @PreAuthorize("@ss.hasPermi('trace:outboundUDI:add')")
    @Log(title = "销售出库UDI信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SaleOutboundUdi saleOutboundUdi)
    {
        return toAjax(saleOutboundUdiService.insertSaleOutboundUdi(saleOutboundUdi));
    }

    /**
     * 修改销售出库UDI信息
     */
    @PreAuthorize("@ss.hasPermi('trace:outboundUDI:edit')")
    @Log(title = "销售出库UDI信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SaleOutboundUdi saleOutboundUdi)
    {
        return toAjax(saleOutboundUdiService.updateSaleOutboundUdi(saleOutboundUdi));
    }

    /**
     * 删除销售出库UDI信息
     */
    @PreAuthorize("@ss.hasPermi('trace:outboundUDI:remove')")
    @Log(title = "销售出库UDI信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(saleOutboundUdiService.deleteSaleOutboundUdiByIds(ids));
    }
}
