package com.easybuy.backend.service.impl.user;

import com.alibaba.fastjson.JSONObject;
import com.easybuy.backend.mapper.EasybuyUserMapper;
import com.easybuy.backend.pojo.EasybuyUser;
import com.easybuy.backend.service.user.ModifyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ModifyUserServiceImpl implements ModifyUserService {

    @Autowired
    private EasybuyUserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public JSONObject modifyuser(Integer id,String username, String password, String name, Integer sex, String identitycode, String email, String mobile, Integer type) {
        JSONObject resp = new JSONObject();
        resp.put("result","error");
        if(password != null && password.length() == 0) password = null;
        if (password != null && password.length() > 20) {
            resp.put("message","密码长度不能大于20");
            return resp;
        }
        if (password != null && password.length() < 6) {
            resp.put("message","密码长度不能小于6");
            return resp;
        }
        if (password != null)password=passwordEncoder.encode(password);
        EasybuyUser user = new EasybuyUser(id,username,name,password,sex,identitycode,email,mobile,type);
        userMapper.updateById(user);
        resp.put("result","success");
        return resp;
    }
}
