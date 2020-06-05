package com.wd.sellergoods.service;

import com.wd.entity.PageResult;
import com.wd.pojo.TbBrand;

import java.util.List;
import java.util.Map;

public interface BrandService {

    List<Map> selectOptionList();

    public List<TbBrand> findAll();

    public PageResult findPage(int pageNum,int pageSize);

    public void add(TbBrand tbBrand);

    public void update(TbBrand tbBrand);//修改

    public TbBrand findOne(long id);//根据id获取实体

    public void del(long[] ids);

    public PageResult findPage(TbBrand tbBrand,int pageNum,int pageSize);
}
