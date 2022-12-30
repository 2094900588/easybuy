package com.easybuy.backend.service.news;

import com.alibaba.fastjson.JSONObject;

public interface GetNewsListService {
    public JSONObject getlist(Integer page,Integer pagesize);
}
