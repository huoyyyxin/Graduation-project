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
import com.ruoyi.trace.domain.WarehouseType;
import com.ruoyi.trace.service.IWarehouseTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * warehouse_typeController
 * 
 * @author zhang
 * @date 2024-01-26
 */
@RestController
@RequestMapping("/trace/warehouse_type")
public class WarehouseTypeController extends BaseController
{
    @Autowired
    private IWarehouseTypeService warehouseTypeService;

    /**
     * 查询warehouse_type列表
     */
    @PreAuthorize("@ss.hasPermi('trace:warehouse_type:list')")
    @GetMapping("/list")
    public TableDataInfo list(WarehouseType warehouseType)
    {
        startPage();
        List<WarehouseType> list = warehouseTypeService.selectWarehouseTypeList(warehouseType);
        return getDataTable(list);
    }

    /**
     * 导出warehouse_type列表
     */
    @PreAuthorize("@ss.hasPermi('trace:warehouse_type:export')")
    @Log(title = "warehouse_type", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WarehouseType warehouseType)
    {
        List<WarehouseType> list = warehouseTypeService.selectWarehouseTypeList(warehouseType);
        ExcelUtil<WarehouseType> util = new ExcelUtil<WarehouseType>(WarehouseType.class);
        util.exportExcel(response, list, "warehouse_type数据");
    }

    /**
     * 获取warehouse_type详细信息
     */
    @PreAuthorize("@ss.hasPermi('trace:warehouse_type:query')")
    @GetMapping(value = "/{warehouseTypeId}")
    public AjaxResult getInfo(@PathVariable("warehouseTypeId") Long warehouseTypeId)
    {
        return success(warehouseTypeService.selectWarehouseTypeByWarehouseTypeId(warehouseTypeId));
    }

    /**
     * 新增warehouse_type
     */
    @PreAuthorize("@ss.hasPermi('trace:warehouse_type:add')")
    @Log(title = "warehouse_type", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WarehouseType warehouseType)
    {
        return toAjax(warehouseTypeService.insertWarehouseType(warehouseType));
    }

    /**
     * 修改warehouse_type
     */
    @PreAuthorize("@ss.hasPermi('trace:warehouse_type:edit')")
    @Log(title = "warehouse_type", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WarehouseType warehouseType)
    {
        return toAjax(warehouseTypeService.updateWarehouseType(warehouseType));
    }

    /**
     * 删除warehouse_type
     */
    @PreAuthorize("@ss.hasPermi('trace:warehouse_type:remove')")
    @Log(title = "warehouse_type", businessType = BusinessType.DELETE)
	@DeleteMapping("/{warehouseTypeIds}")
    public AjaxResult remove(@PathVariable Long[] warehouseTypeIds)
    {
        return toAjax(warehouseTypeService.deleteWarehouseTypeByWarehouseTypeIds(warehouseTypeIds));
    }
}
