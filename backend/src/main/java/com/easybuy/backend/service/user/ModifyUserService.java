package com.easybuy.backend.service.user;

import com.alibaba.fastjson.JSONObject;

public interface ModifyUserService {
    public JSONObject modifyuser(Integer id,String username, String password, String name, Integer sex, String identitycode, String email, String mobile,Integer type);
}
