package com.littlebuddha.recruit.modules.base.controller;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ck
 * @date 2021/2/24 14:43
 */
public abstract class BaseController {

    /*public<T> JsonResult getLayUiData(PageInfo pageInfo){
        List data = pageInfo.getList();
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(0);
        jsonResult.setMsg("成功");
        jsonResult.setData(data);
        int pages = pageInfo.getPages();
        int pageSize = pageInfo.getPageSize();
        BigDecimal data1 = new BigDecimal(pages);
        BigDecimal data2 = new BigDecimal(pageSize);
        jsonResult.setCount((data1.multiply(data2)).intValue());
        return jsonResult;
    }*/

    /**
     * 获取bootstrap data分页数据
     * @param
     * @return map对象
     */
    public <E> Map<String, Object> getBootstrapData(PageInfo<E> pageInfo){
        Map<String, Object> map = new HashMap<String, Object>();
        List<E> list = pageInfo.getList();
        map.put("rows", list);
        map.put("total", pageInfo.getTotal());
        return map;
    }
}
