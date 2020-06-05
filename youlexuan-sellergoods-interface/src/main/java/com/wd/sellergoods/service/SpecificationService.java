package com.wd.sellergoods.service;

import com.wd.entity.PageResult;
import com.wd.group.SpecificationVO;
import com.wd.pojo.TbSpecification;

import java.util.List;
import java.util.Map;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface SpecificationService {


	List<Map> selectOptionList();


	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbSpecification> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);


	/**
	 * 增加
	*/
	public void add(TbSpecification specification);

	//组合实体类
	public void insert(SpecificationVO specificationVO);


	/**
	 * 修改
	 */
	public void update(SpecificationVO specificationVO);


	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public SpecificationVO findOne(Long id);


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
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize);
	
}
