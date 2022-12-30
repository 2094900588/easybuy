package com.easybuy.backend.service.impl.user;

import com.alibaba.fastjson.JSONObject;
import com.easybuy.backend.pojo.EasybuyUser;
import com.easybuy.backend.service.impl.utils.UserDetailsImpl;
import com.easybuy.backend.service.user.LoginService;
import com.easybuy.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServerImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public JSONObject login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username,password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        EasybuyUser user = loginUser.getUser();
        String jwt = JwtUtil.createJWT(user.getId().toString());
        JSONObject map = new JSONObject();
        map.put("result","success");
        map.put("token",jwt);

        return map;
    }
}
