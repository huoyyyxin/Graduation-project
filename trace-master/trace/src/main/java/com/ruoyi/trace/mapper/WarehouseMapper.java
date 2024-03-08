package com.ruoyi.trace.mapper;

import java.util.List;
import com.ruoyi.trace.domain.Warehouse;

/**
 * warehouseMapper接口
 * 
 * @author zhang
 * @date 2024-01-25
 */
public interface WarehouseMapper 
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
     * 删除warehouse
     * 
     * @param id warehouse主键
     * @return 结果
     */
    public int deleteWarehouseById(Long id);

    /**
     * 批量删除warehouse
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWarehouseByIds(Long[] ids);
}
