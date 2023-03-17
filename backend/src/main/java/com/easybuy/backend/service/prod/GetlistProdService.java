package com.easybuy.backend.service.prod;

import com.alibaba.fastjson.JSONObject;

public interface GetlistProdService {
    public JSONObject getlist(String keyword,Integer level1,Integer level2,Integer level3);
}
