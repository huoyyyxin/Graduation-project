package com.ruoyi.trace.service;

import java.util.List;
import com.ruoyi.trace.domain.Warehouse;

/**
 * warehouseService接口
 * 
 * @author zhang
 * @date 2024-01-25
 */
public interface IWarehouseService 
{
    /**
     * 查询warehouse
     * 
     * @param id warehouse主键
     * @return warehouse
     */
    public Warehouse selectWarehouseById(Long id);

    /**
     * 查询warehouse列表
     * 
     * @param warehouse warehouse
     * @return warehouse集合
     */
    public List<Warehouse> selectWarehouseList(Warehouse warehouse);

    /**
     * 新增warehouse
     * 
     * @param warehouse warehouse
     * @return 结果
     */
    public int insertWarehouse(Warehouse warehouse);

    /**
     * 修改warehouse
     * 
     * @param warehouse warehouse
     * @return 结果
     */
    public int updateWarehouse(Warehouse warehouse);

    /**
     * 批量删除warehouse
     * 
     * @param ids 需要删除的warehouse主键集合
     * @return 结果
     */
    public int deleteWarehouseByIds(Long[] ids);

    /**
     * 删除warehouse信息
     * 
     * @param id warehouse主键
     * @return 结果
     */
    public int deleteWarehouseById(Long id);
}
