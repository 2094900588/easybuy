package com.easybuy.backend.service.impl.user;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easybuy.backend.mapper.EasybuyUserMapper;
import com.easybuy.backend.pojo.EasybuyUser;
import com.easybuy.backend.service.user.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    EasybuyUserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public JSONObject register(String username, String password, String name,
                               Integer sex, String identitycode, String email, String mobile) {
        JSONObject resp = new JSONObject();
        resp.put("result","error");
        if (username == null) {
            resp.put("message","用户名不能为空");
            return resp;
        }
        if (password == null) {
            resp.put("message","密码不能为空");
            return resp;
        }

        username = username.trim();
        if (username.length() == 0) {
            resp.put("message","用户名不能为空");
            return resp;
        }

        if (password.length() == 0) {
            resp.put("message","密码不能为空");
            return resp;
        }

        if (username.length() > 20 ) {
            resp.put("message","用户名长度不能大于20");
            return resp;
        }

        if (password.length() > 20) {
            resp.put("message","密码长度不能大于20");
            return resp;
        }

        QueryWrapper<EasybuyUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("loginname",username);
        List<EasybuyUser> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            resp.put("message","用户名已存在");
            return resp;
        }

        String encodedPassword = passwordEncoder.encode(password);
        EasybuyUser user = new EasybuyUser(null,username,name,encodedPassword,sex,identitycode,email,mobile,0);
        userMapper.insert(user);
        resp.put("result","success");

        return resp;
    }
}
