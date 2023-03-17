package com.easybuy.backend.service.categroy;

import com.alibaba.fastjson.JSONObject;

public interface CateGroyService {
    public JSONObject add(String name,Integer parentId,Integer type);
    public JSONObject del(Integer id);
    public JSONObject mod(Integer id,String name,Integer parentId,Integer type);
}
