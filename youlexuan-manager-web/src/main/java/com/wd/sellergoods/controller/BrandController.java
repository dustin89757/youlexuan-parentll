package com.wd.sellergoods.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.wd.entity.PageResult;
import com.wd.entity.Result;
import com.wd.pojo.TbBrand;
import com.wd.sellergoods.service.BrandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        return brandService.selectOptionList();
    }
    @RequestMapping("/findAll")
    public List<TbBrand> findAll(){
        return brandService.findAll();
    }
    @RequestMapping("/findPage")
    public PageResult findPage(int pageNum,int pageSize){
        return brandService.findPage(pageNum, pageSize);
    }
    @RequestMapping("/add")
    public Result add(@RequestBody TbBrand tbBrand){
        try {
            brandService.add(tbBrand);
            return new Result(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }
    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand tbBrand){
        try {
            brandService.update(tbBrand);
            return new Result(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"修改失败");
        }
    }

    @RequestMapping("/findOne")
    public TbBrand findOne(long id){
       return brandService.findOne(id);
    }

    @RequestMapping("/del")
    public Result del(long[] ids){
        try {
            brandService.del(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBrand tbBrand,int pageNum,int pageSize){
        return brandService.findPage(tbBrand,pageNum,pageSize);
    }
}
