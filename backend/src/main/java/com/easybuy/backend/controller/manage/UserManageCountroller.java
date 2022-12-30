package com.easybuy.backend.controller.manage;

import com.alibaba.fastjson.JSONObject;
import com.easybuy.backend.service.user.AddUserService;
import com.easybuy.backend.service.user.DeleteUserService;
import com.easybuy.backend.service.user.GetUserListService;
import com.easybuy.backend.service.user.ModifyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/manage/user/")
public class UserManageCountroller {


    @Autowired
    private GetUserListService getUserListService;

    @Autowired
    private DeleteUserService deleteUserService;

    @Autowired
    private ModifyUserService modifyUserService;

    @Autowired
    private AddUserService addUserService;

    @GetMapping("/getuserlist")
    public JSONObject getuserlist(@RequestParam Map<String, String> data) {
        int page = 1;
        int pagesize = 10;
        if(data.get("page") !=null)
            page = Integer.parseInt(data.get("page"));
        if(data.get("pagesize")!=null)
            pagesize = Integer.parseInt(data.get("pagesize"));
        return getUserListService.getuserlist(page,pagesize);
    }

    @GetMapping("/delete")
    public JSONObject deleteuser(@RequestParam Map<String, String> data) {
        Integer userid = Integer.parseInt(data.get("userid"));
        return deleteUserService.deleteuser(userid);
    }

    @PostMapping("/modify")
    public JSONObject modifyuser(@RequestBody JSONObject map) {
        String username = map.getString("username");
        String password = map.getString("password");
        String name = map.getString("name");
        Integer sex = map.getInteger("sex");
        String identitycode = map.getString("identitycode");
        String email = map.getString("email");
        String mobile = map.getString("mobile");
        Integer type = map.getInteger("type");
        Integer userid = map.getInteger("userid");
        return modifyUserService.modifyuser(userid,username,password,name,sex,identitycode,email,mobile,type);
    }

    @PostMapping("/add")
    public JSONObject adduser(@RequestBody JSONObject map) {
        String username = map.getString("username");
        String password = map.getString("password");
        String name = map.getString("name");
        Integer sex = map.getInteger("sex");
        String identitycode = map.getString("identitycode");
        String email = map.getString("email");
        String mobile = map.getString("mobile");
        Integer type = map.getInteger("type");

        return addUserService.adduser(username,password,name,sex,identitycode,email,mobile,type);
    }


}
