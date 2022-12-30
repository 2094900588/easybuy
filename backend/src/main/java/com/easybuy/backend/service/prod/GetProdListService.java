package com.easybuy.backend.service.prod;

import com.alibaba.fastjson.JSONObject;

public interface GetProdListService {
    public JSONObject getlist(Integer page,Integer pagesize);
}
