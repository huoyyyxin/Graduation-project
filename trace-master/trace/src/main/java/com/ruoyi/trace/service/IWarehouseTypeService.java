package com.ruoyi.trace.service;

import java.util.List;
import com.ruoyi.trace.domain.WarehouseType;

/**
 * warehouse_typeService接口
 * 
 * @author zhang
 * @date 2024-01-26
 */
public interface IWarehouseTypeService 
{
    /**
     * 查询warehouse_type
     * 
     * @param warehouseTypeId warehouse_type主键
     * @return warehouse_type
     */
    public WarehouseType selectWarehouseTypeByWarehouseTypeId(Long warehouseTypeId);

    /**
     * 查询warehouse_type列表
     * 
     * @param warehouseType warehouse_type
     * @return warehouse_type集合
     */
    public List<WarehouseType> selectWarehouseTypeList(WarehouseType warehouseType);

    /**
     * 新增warehouse_type
     * 
     * @param warehouseType warehouse_type
     * @return 结果
     */
    public int insertWarehouseType(WarehouseType warehouseType);

    /**
     * 修改warehouse_type
     * 
     * @param warehouseType warehouse_type
     * @return 结果
     */
    public int updateWarehouseType(WarehouseType warehouseType);

    /**
     * 批量删除warehouse_type
     * 
     * @param warehouseTypeIds 需要删除的warehouse_type主键集合
     * @return 结果
     */
    public int deleteWarehouseTypeByWarehouseTypeIds(Long[] warehouseTypeIds);

    /**
     * 删除warehouse_type信息
     * 
     * @param warehouseTypeId warehouse_type主键
     * @return 结果
     */
    public int deleteWarehouseTypeByWarehouseTypeId(Long warehouseTypeId);
}
