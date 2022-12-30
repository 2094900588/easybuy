package com.easybuy.backend.service.impl.user;

import com.alibaba.fastjson.JSONObject;
import com.easybuy.backend.pojo.EasybuyUser;
import com.easybuy.backend.service.impl.utils.UserDetailsImpl;
import com.easybuy.backend.service.user.InfoService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class InfoServiceImpl implements InfoService {
    @Override
    public JSONObject getinfo() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        EasybuyUser user = loginUser.getUser();
        JSONObject resp = new JSONObject();
        resp.put("id",user.getId());
        resp.put("username",user.getLoginname());
        resp.put("name",user.getUsername());
        resp.put("sex",user.getSex());
        resp.put("email",user.getEmail());
        resp.put("mobile",user.getMobile());
        resp.put("usertype",user.getType());
        return resp;
    }
}
