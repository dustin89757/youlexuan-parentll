package com.wd.solrutil;

import com.alibaba.fastjson.JSON;
import com.wd.mapper.TbItemMapper;
import com.wd.pojo.TbItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:spring/applicationContext-*.xml")
public class SolrTest {

    @Autowired
    SolrTemplate solrTemplate;

    @Autowired
    TbItemMapper itemMapper;

    /*删除全部*/
    @Test
    public void test1(){
        SimpleQuery simpleQuery = new SimpleQuery("*:*");
        solrTemplate.delete(simpleQuery);
        solrTemplate.commit();
    }

    /*
     * 导入数据
     * */
    @Test
    public void test2(){
        List<TbItem> list = itemMapper.selectByExample(null);

        for (TbItem item : list) {
            Map map = JSON.parseObject(item.getSpec());
            item.setSpecMap(map);
        }

        solrTemplate.saveBeans(list);
        solrTemplate.commit();
    }


}
