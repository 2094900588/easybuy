package com.easybuy.backend.service.user;

import com.alibaba.fastjson.JSONObject;

public interface UserAddressService {
    public JSONObject getuseraddress();
    public JSONObject adduseraddress(String address,Integer isdefault,String remark);
    public JSONObject deluseraddress(Integer id);
    public JSONObject modifyuseraddress(Integer id,String address,Integer isdefault,String remark);
}
