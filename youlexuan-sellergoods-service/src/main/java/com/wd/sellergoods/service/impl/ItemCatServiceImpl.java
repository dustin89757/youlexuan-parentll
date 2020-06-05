package com.wd.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wd.entity.PageResult;
import com.wd.mapper.TbItemCatMapper;
import com.wd.pojo.TbItemCat;
import com.wd.pojo.TbItemCatExample;
import com.wd.pojo.TbItemCatExample.Criteria;
import com.wd.sellergoods.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * 商品类目服务实现层
 *
 * @author Administrator
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;
	@Autowired
	private RedisTemplate redisTemplate;

	private void categoryHash(){
		List<TbItemCat> list = findAll();//所有的分类
		for (TbItemCat itemCat : list) {

			if(itemCat.getName()!=null && itemCat.getName().length()>0 && itemCat.getTypeId()!=null){
				String name = itemCat.getName();//分类名称
				Long typeId = itemCat.getTypeId();//模板id
				redisTemplate.boundHashOps("categoryHash").put(name,typeId);
			}
		}
	}

	@Override
	public List<TbItemCat> findByParentId(long parentId) {
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria1 = example.createCriteria();
		criteria1.andParentIdEqualTo(parentId);
		List<TbItemCat> tbItemCats = itemCatMapper.selectByExample(example);
		categoryHash();//加载缓存
		System.out.println("分类模板放进了reids");
		return tbItemCats;
	}

	/**
     * 查询全部
     */
    @Override
    public List<TbItemCat> findAll() {
        return itemCatMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbItemCat> page = (Page<TbItemCat>) itemCatMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TbItemCat itemCat) {
        itemCatMapper.insert(itemCat);
    }


    /**
     * 修改
     */
    @Override
    public void update(TbItemCat itemCat) {
        itemCatMapper.updateByPrimaryKey(itemCat);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbItemCat findOne(Long id) {
        return itemCatMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            itemCatMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public PageResult findPage(TbItemCat itemCat, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbItemCatExample example = new TbItemCatExample();
        Criteria criteria = example.createCriteria();

        if (itemCat != null) {
            if (itemCat.getName() != null && itemCat.getName().length() > 0) {
                criteria.andNameLike("%" + itemCat.getName() + "%");
            }
        }

        Page<TbItemCat> page = (Page<TbItemCat>) itemCatMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

}
