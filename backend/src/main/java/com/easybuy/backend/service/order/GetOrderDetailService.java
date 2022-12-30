package com.easybuy.backend.service.order;

import com.alibaba.fastjson.JSONObject;

public interface GetOrderDetailService {
    public JSONObject getorderdetail(Integer orderid);
}
