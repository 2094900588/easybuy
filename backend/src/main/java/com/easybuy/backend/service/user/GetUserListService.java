package com.easybuy.backend.service.user;

import com.alibaba.fastjson.JSONObject;

public interface GetUserListService {
    public JSONObject getuserlist(Integer page,Integer pagesize);
}
