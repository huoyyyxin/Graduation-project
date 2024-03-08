package com.ruoyi.trace.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.trace.mapper.WarehouseMapper;
import com.ruoyi.trace.domain.Warehouse;
import com.ruoyi.trace.service.IWarehouseService;

/**
 * warehouseService业务层处理
 * 
 * @author zhang
 * @date 2024-01-25
 */
@Service
public class WarehouseServiceImpl implements IWarehouseService 
{
    @Autowired
    private WarehouseMapper warehouseMapper;

    /**
     * 查询warehouse
     * 
     * @param id warehouse主键
     * @return warehouse
     */
    @Override
    public Warehouse selectWarehouseById(Long id)
    {
        return warehouseMapper.selectWarehouseById(id);
    }

    /**
     * 查询warehouse列表
     * 
     * @param warehouse warehouse
     * @return warehouse
     */
    @Override
    public List<Warehouse> selectWarehouseList(Warehouse warehouse)
    {
        return warehouseMapper.selectWarehouseList(warehouse);
    }

    /**
     * 新增warehouse
     * 
     * @param warehouse warehouse
     * @return 结果
     */
    @Override
    public int insertWarehouse(Warehouse warehouse)
    {
        //去厨房拿食材后进行料理。
        warehouse.setCreateTime(DateUtils.getNowDate());
        //从厨房拿到食材的操作
        int i = warehouseMapper.insertWarehouse(warehouse);
        return i;
    }

    /**
     * 修改warehouse
     * 
     * @param warehouse warehouse
     * @return 结果
     */
    @Override
    public int updateWarehouse(Warehouse warehouse)
    {
        warehouse.setUpdateTime(DateUtils.getNowDate());
        return warehouseMapper.updateWarehouse(warehouse);
    }

    /**
     * 批量删除warehouse
     * 
     * @param ids 需要删除的warehouse主键
     * @return 结果
     */
    @Override
    public int deleteWarehouseByIds(Long[] ids)
    {
        return warehouseMapper.deleteWarehouseByIds(ids);
    }

    /**
     * 删除warehouse信息
     * 
     * @param id warehouse主键
     * @return 结果
     */
    @Override
    public int deleteWarehouseById(Long id)
    {
        return warehouseMapper.deleteWarehouseById(id);
    }
}
