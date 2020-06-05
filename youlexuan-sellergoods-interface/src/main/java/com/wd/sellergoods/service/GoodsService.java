package com.wd.sellergoods.service;

import com.wd.entity.PageResult;
import com.wd.group.Goods;
import com.wd.pojo.TbGoods;
import com.wd.pojo.TbItem;

import java.util.List;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface GoodsService {

	/**
	 * 根据商品ID和状态查询Item表信息
	 * @param goodsIds
	 * @param status
	 * @return
	 */
	public List<TbItem> findItemListByGoodsIdandStatus(Long[] goodsIds, String status );


	/**
	 * 批量修改状态
	 * @param ids
	 * @param status
	 */
	public void updateStatus(Long []ids,String status);
	/**
	 * 增加，传递组合参数
	 */
	public void add(Goods goods);

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbGoods> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);


	/**
	 * 增加
	*/
	public void add(TbGoods goods);


	/**
	 * 修改
	 */
	public void update(Goods goods);


	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public Goods findOne(Long id);


	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long[] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(TbGoods goods, int pageNum, int pageSize);

    void updateIsMarketable(Long[] ids, String isMarketable);
}
