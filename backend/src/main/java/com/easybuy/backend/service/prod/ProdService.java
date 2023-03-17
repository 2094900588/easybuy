package com.easybuy.backend.service.prod;

import com.alibaba.fastjson.JSONObject;

public interface ProdService {
    public JSONObject add(String name,String description,Float price, Integer stock,Integer categoryLevel1Id,
                          Integer categoryLevel2Id,Integer categoryLevel3Id,String fileName,Integer isDelete);
    public JSONObject del(Integer id);
    public JSONObject mod(Integer id,String name,String description,Float price, Integer stock,Integer categoryLevel1Id,
                          Integer categoryLevel2Id,Integer categoryLevel3Id,String fileName,Integer isDelete);
}
