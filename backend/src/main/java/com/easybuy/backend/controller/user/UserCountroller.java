package com.easybuy.backend.controller.user;


import com.alibaba.fastjson.JSONObject;
import com.easybuy.backend.service.user.GetUserListService;
import com.easybuy.backend.service.user.InfoService;
import com.easybuy.backend.service.user.LoginService;
import com.easybuy.backend.service.user.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user/")
public class UserCountroller {

    @Autowired
    private LoginService loginService;
    @Autowired
    private InfoService infoService;

    @Autowired
    private RegisterService registerService;


    @PostMapping("/login")
    public JSONObject login(@RequestBody JSONObject map){
        String username = map.getString("username");
        String password = map.getString("password");
        return loginService.login(username,password);
    }

    @PostMapping("/register")//(String username, String password, String name,
    //Integer sex, String identitycode, String email, String mobile)
    public JSONObject register(@RequestBody JSONObject map){
        String username = map.getString("username");
        String password = map.getString("password");
        String name = map.getString("name");
        Integer sex = map.getInteger("sex");
        String identitycode = map.getString("identitycode");
        String email = map.getString("email");
        String mobile = map.getString("mobile");

        return registerService.register(username,password,name,sex,identitycode,email,mobile);
    }

    @GetMapping("/getinfo")
    public JSONObject getinfo(){
        return infoService.getinfo();
    }


}
