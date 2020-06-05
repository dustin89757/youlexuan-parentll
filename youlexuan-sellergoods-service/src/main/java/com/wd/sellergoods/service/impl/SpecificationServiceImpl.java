package com.wd.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wd.entity.PageResult;
import com.wd.group.SpecificationVO;
import com.wd.mapper.TbSpecificationMapper;
import com.wd.mapper.TbSpecificationOptionMapper;
import com.wd.pojo.TbSpecification;
import com.wd.pojo.TbSpecificationExample;
import com.wd.pojo.TbSpecificationExample.Criteria;
import com.wd.pojo.TbSpecificationOption;
import com.wd.pojo.TbSpecificationOptionExample;
import com.wd.sellergoods.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private TbSpecificationMapper specificationMapper;
    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;

    @Override
    public List<Map> selectOptionList() {
        return specificationMapper.selectOptionList();
    }

    /**
     * 查询全部
     */
    @Override
    public List<TbSpecification> findAll() {
        return specificationMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TbSpecification specification) {
        specificationMapper.insert(specification);
    }

    @Override
    public void insert(SpecificationVO specificationVO) {
        //向两张表假数据
        specificationMapper.insert(specificationVO.getSpecification());//添加规格


        List<TbSpecificationOption> optionList = specificationVO.getSpecificationOptionList();
        for (TbSpecificationOption specificationOption : optionList) {
            specificationOption.setSpecId(specificationVO.getSpecification().getId());
			specificationOptionMapper.insert(specificationOption);
        }
    }

    @Override
    public void update(SpecificationVO specificationVO) {
        //保存修改的规格
        specificationMapper.updateByPrimaryKey(specificationVO.getSpecification());
        //根据id删除原有规格选项
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        //指定规格id为条件
        criteria.andSpecIdEqualTo(specificationVO.getSpecification().getId());
        //删除
        specificationOptionMapper.deleteByExample(example);

        for(TbSpecificationOption specificationOption: specificationVO.getSpecificationOptionList()){
            specificationOption.setSpecId(specificationVO.getSpecification().getId());
            specificationOptionMapper.insert(specificationOption);
        }
    }


    /**
     * 修改
     */
   /* @Override
    public void update(TbSpecification specification) {
        specificationMapper.updateByPrimaryKey(specification);
    }*/

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public SpecificationVO findOne(Long id) {

        //查询规格
        TbSpecification tbSpecification=specificationMapper.selectByPrimaryKey(id);
        //查询规格选项列表
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        //根据规格ID查询
        criteria.andSpecIdEqualTo(id);
        List<TbSpecificationOption> options = specificationOptionMapper.selectByExample(example);

        //构建组合实体类的返回结果
        SpecificationVO vo = new SpecificationVO();
        vo.setSpecification(tbSpecification);
        vo.setSpecificationOptionList(options);
        return vo;


    }


    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            specificationMapper.deleteByPrimaryKey(id);
            //删除规格选项
            TbSpecificationOptionExample example = new TbSpecificationOptionExample();
            TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(id);//指定id
            specificationOptionMapper.deleteByExample(example);//删除

        }
    }


    @Override
    public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbSpecificationExample example = new TbSpecificationExample();
        Criteria criteria = example.createCriteria();

        if (specification != null) {
            if (specification.getSpecName() != null && specification.getSpecName().length() > 0) {
                criteria.andSpecNameLike("%" + specification.getSpecName() + "%");
            }
        }

        Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

}
