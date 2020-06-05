package com.wd.group;


import com.wd.pojo.TbSpecification;
import com.wd.pojo.TbSpecificationOption;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/*规格+规格选项列表的组合类*/
@Data
public class SpecificationVO implements Serializable {

    private TbSpecification specification;
    private List<TbSpecificationOption> specificationOptionList;

}
