package com.ruoyi.trace.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.trace.mapper.WarehouseTypeMapper;
import com.ruoyi.trace.domain.WarehouseType;
import com.ruoyi.trace.service.IWarehouseTypeService;

/**
 * warehouse_typeService业务层处理
 * 
 * @author zhang
 * @date 2024-01-26
 */
@Service
public class WarehouseTypeServiceImpl implements IWarehouseTypeService 
{
    @Autowired
    private WarehouseTypeMapper warehouseTypeMapper;

    /**
     * 查询warehouse_type
     * 
     * @param warehouseTypeId warehouse_type主键
     * @return warehouse_type
     */
    @Override
    public WarehouseType selectWarehouseTypeByWarehouseTypeId(Long warehouseTypeId)
    {
        return warehouseTypeMapper.selectWarehouseTypeByWarehouseTypeId(warehouseTypeId);
    }

    /**
     * 查询warehouse_type列表
     * 
     * @param warehouseType warehouse_type
     * @return warehouse_type
     */
    @Override
    public List<WarehouseType> selectWarehouseTypeList(WarehouseType warehouseType)
    {
        return warehouseTypeMapper.selectWarehouseTypeList(warehouseType);
    }

    /**
     * 新增warehouse_type
     * 
     * @param warehouseType warehouse_type
     * @return 结果
     */
    @Override
    public int insertWarehouseType(WarehouseType warehouseType)
    {
        warehouseType.setCreateTime(DateUtils.getNowDate());
        return warehouseTypeMapper.insertWarehouseType(warehouseType);
    }

    /**
     * 修改warehouse_type
     * 
     * @param warehouseType warehouse_type
     * @return 结果
     */
    @Override
    public int updateWarehouseType(WarehouseType warehouseType)
    {
        warehouseType.setUpdateTime(DateUtils.getNowDate());
        return warehouseTypeMapper.updateWarehouseType(warehouseType);
    }

    /**
     * 批量删除warehouse_type
     * 
     * @param warehouseTypeIds 需要删除的warehouse_type主键
     * @return 结果
     */
    @Override
    public int deleteWarehouseTypeByWarehouseTypeIds(Long[] warehouseTypeIds)
    {
        return warehouseTypeMapper.deleteWarehouseTypeByWarehouseTypeIds(warehouseTypeIds);
    }

    /**
     * 删除warehouse_type信息
     * 
     * @param warehouseTypeId warehouse_type主键
     * @return 结果
     */
    @Override
    public int deleteWarehouseTypeByWarehouseTypeId(Long warehouseTypeId)
    {
        return warehouseTypeMapper.deleteWarehouseTypeByWarehouseTypeId(warehouseTypeId);
    }
}
