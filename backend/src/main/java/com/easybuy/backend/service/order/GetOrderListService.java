package com.easybuy.backend.service.order;

import com.alibaba.fastjson.JSONObject;

public interface GetOrderListService {
    public JSONObject getlist(Integer page,Integer pagesize);
}
