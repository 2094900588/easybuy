package com.easybuy.backend.service.categroy;

import com.alibaba.fastjson.JSONObject;

public interface GetCategroyListService {
    public JSONObject getcategroylist(Integer page,Integer pagesize);
}
