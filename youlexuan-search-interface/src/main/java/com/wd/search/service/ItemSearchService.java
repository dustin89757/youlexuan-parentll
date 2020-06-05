package com.wd.search.service;

import com.wd.pojo.TbItem;

import java.util.List;
import java.util.Map;

public interface ItemSearchService {



    /**
     * 删除数据
     * @param goodsIdList
     */
    public void deleteByGoodsIds(List goodsIdList);

    /**
     * 导入数据
     * @param list
     */
    public void importList(List<TbItem> list);

    /**
     * 搜索
     * @param searchMap
     * @return
     */
    public Map<String,Object> search(Map searchMap);
}
