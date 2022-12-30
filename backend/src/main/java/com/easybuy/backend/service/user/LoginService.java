package com.easybuy.backend.service.user;

import com.alibaba.fastjson.JSONObject;


public interface LoginService {
    public JSONObject login(String username, String password);
}
