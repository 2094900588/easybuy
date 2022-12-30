package com.easybuy.backend.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.easybuy.backend.service.user.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user/")
public class UserAddressCountroller {

    @Autowired
    private UserAddressService userAddressService;

    @GetMapping("/getaddress")
    public JSONObject getaddress(){
        return userAddressService.getuseraddress();
    }

    @GetMapping("/deladdress")
    public JSONObject deladdress(@RequestParam Map<String, String> data){
        Integer id = Integer.parseInt(data.get("id"));
        return userAddressService.deluseraddress(id);
    }

    @PostMapping("/addaddress")
    public JSONObject addaddress(@RequestBody JSONObject map){
        String address = map.getString("address");
        String remark = map.getString("remark");
        Integer isdefault = map.getInteger("isdefault");
        return userAddressService.adduseraddress(address,isdefault,remark);
    }

    @PostMapping("/modifyaddress")
    public JSONObject modifyaddress(@RequestBody JSONObject map){
        Integer id = map.getInteger("id");
        String address = map.getString("address");
        String remark = map.getString("remark");
        Integer isdefault = map.getInteger("isdefault");
        return userAddressService.modifyuseraddress(id,address,isdefault,remark);
    }


}
