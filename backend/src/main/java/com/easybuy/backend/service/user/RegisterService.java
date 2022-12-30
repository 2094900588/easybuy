package com.easybuy.backend.service.user;

import com.alibaba.fastjson.JSONObject;


public interface RegisterService {
    public JSONObject register(String username, String password,String name,Integer sex,String identitycode,String email,String mobile);
}
