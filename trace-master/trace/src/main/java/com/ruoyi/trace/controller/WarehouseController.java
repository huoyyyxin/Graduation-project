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
import com.ruoyi.trace.domain.Warehouse;
import com.ruoyi.trace.service.IWarehouseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * warehouseController
 * 
 * @author zhang
 * @date 2024-01-25
 */
//前端向后台进行请求-》get post get-》/trace/warehouse/list
@RestController
@RequestMapping("/trace/warehouse")
public class WarehouseController extends BaseController
{
    @Autowired
    private IWarehouseService warehouseService;

    /**
     * 查询warehouse列表
     */
    @PreAuthorize("@ss.hasPermi('trace:warehouse:list')")
    @GetMapping("/list")
    public TableDataInfo list(Warehouse warehouse)
    {
        startPage();
        List<Warehouse> list = warehouseService.selectWarehouseList(warehouse);
        return getDataTable(list);
    }

    // c r u d 增删改查

    /**
     * 导出warehouse列表
     */
    @PreAuthorize("@ss.hasPermi('trace:warehouse:export')")
    @Log(title = "warehouse", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Warehouse warehouse)
    {
        List<Warehouse> list = warehouseService.selectWarehouseList(warehouse);
        ExcelUtil<Warehouse> util = new ExcelUtil<Warehouse>(Warehouse.class);
        util.exportExcel(response, list, "warehouse数据");
    }

    /**
     * 获取warehouse详细信息
     */
    @PreAuthorize("@ss.hasPermi('trace:warehouse:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(warehouseService.selectWarehouseById(id));
    }

    /**
     * 新增warehouse
     */
    @PreAuthorize("@ss.hasPermi('trace:warehouse:add')")
    @Log(title = "warehouse", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Warehouse warehouse)
    {
        return toAjax(warehouseService.insertWarehouse(warehouse));
    }

    /**
     * 修改warehouse update
     */
    @PreAuthorize("@ss.hasPermi('trace:warehouse:edit')")
    @Log(title = "warehouse", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Warehouse warehouse)
    {
        return toAjax(warehouseService.updateWarehouse(warehouse));
    }

    /**
     * 删除warehouse
     */
    @PreAuthorize("@ss.hasPermi('trace:warehouse:remove')")
    @Log(title = "warehouse", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(warehouseService.deleteWarehouseByIds(ids));
    }
}
