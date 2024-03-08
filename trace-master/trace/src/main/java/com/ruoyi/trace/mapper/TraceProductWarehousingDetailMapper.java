package com.ruoyi.trace.mapper;

import java.util.List;
import com.ruoyi.trace.domain.TraceProductWarehousingDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 生产入库明细Mapper接口
 * 
 * @author ruoyi
 * @date 2024-02-24
 */
public interface TraceProductWarehousingDetailMapper 
{
    /**
     * 查询生产入库明细
     * 
     * @param id 生产入库明细主键
     * @return 生产入库明细
     */
    public TraceProductWarehousingDetail selectTraceProductWarehousingDetailById(Long id);

    /**
     * 查询生产入库明细列表
     * 
     * @param traceProductWarehousingDetail 生产入库明细
     * @return 生产入库明细集合
     */
    public List<TraceProductWarehousingDetail> selectTraceProductWarehousingDetailList(TraceProductWarehousingDetail traceProductWarehousingDetail);

    /**
     * 新增生产入库明细
     * 
     * @param traceProductWarehousingDetail 生产入库明细
     * @return 结果
     */
    public int insertTraceProductWarehousingDetail(TraceProductWarehousingDetail traceProductWarehousingDetail);

    /**
     * 修改生产入库明细
     * 
     * @param traceProductWarehousingDetail 生产入库明细
     * @return 结果
     */
    public int updateTraceProductWarehousingDetail(TraceProductWarehousingDetail traceProductWarehousingDetail);

    /**
     * 删除生产入库明细
     * 
     * @param id 生产入库明细主键
     * @return 结果
     */
    public int deleteTraceProductWarehousingDetailById(Long id);

    /**
     * 批量删除生产入库明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTraceProductWarehousingDetailByIds(Long[] ids);

    @Select("select * from trace_product_warehousing_detail where production_warehousing_code = #{id}")
    List<TraceProductWarehousingDetail> getProductionWarehousing(@Param("id") String id);

    @Select("select * from trace_product_warehousing_detail where production_warehousing_code = #{productionWarehousingCode}")
    List<TraceProductWarehousingDetail> getDetail(@Param("productionWarehousingCode") String productionWarehousingCode);

    @Delete("delete from trace_product_warehousing_detail where production_warehousing_code=#{id}")
    void removeDetail(@Param("id") String id);

    TraceProductWarehousingDetail selectDetailWithCode(String code);

    /**
     * 根据tpwid删除
     */
    public int deleteTraceProductionWarehousingDetailByTpwid(String[] ids);
}
