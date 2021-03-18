package com.littlebuddha.recruit.modules.base.controller;

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
}
