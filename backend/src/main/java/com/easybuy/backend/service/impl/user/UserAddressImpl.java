package com.easybuy.backend.service.impl.user;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easybuy.backend.mapper.EasybuyUserAddressMapper;
import com.easybuy.backend.pojo.EasybuyUser;
import com.easybuy.backend.pojo.EasybuyUserAddress;
import com.easybuy.backend.service.impl.utils.UserDetailsImpl;
import com.easybuy.backend.service.user.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserAddressImpl implements UserAddressService {
    @Autowired
    private EasybuyUserAddressMapper easybuyUserAddressMapper;

    @Override
    public JSONObject getuseraddress() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext()
                        .getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        EasybuyUser user = loginUser.getUser();
        QueryWrapper<EasybuyUserAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",user.getId());
        List<EasybuyUserAddress> addresses = easybuyUserAddressMapper.selectList(queryWrapper);
        JSONObject resp = new JSONObject();
        resp.put("result","success");
        resp.put("address",addresses);
        return resp;
    }

    @Override
    public JSONObject adduseraddress(String address, Integer isdefault,String remark) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext()
                        .getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        EasybuyUser user = loginUser.getUser();
        EasybuyUserAddress userAddress = new EasybuyUserAddress(null, user.getId(), address,new Date(),isdefault,remark);
        easybuyUserAddressMapper.insert(userAddress);
        JSONObject resp = new JSONObject();
        resp.put("result","success");
        return resp;
    }

    @Override
    public JSONObject deluseraddress(Integer id) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext()
                        .getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        EasybuyUser user = loginUser.getUser();
        JSONObject resp = new JSONObject();
        if (easybuyUserAddressMapper.selectById(id).getUserid().equals(user.getId())){
            easybuyUserAddressMapper.deleteById(id);
            resp.put("result","success");
        } else {
            resp.put("result","error");
            resp.put("message","地址不存在");
        }
        return resp;
    }

    @Override
    public JSONObject modifyuseraddress(Integer id,String address, Integer isdefault,String remark) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext()
                        .getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        EasybuyUser user = loginUser.getUser();
        QueryWrapper<EasybuyUserAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        queryWrapper.eq("userid",user.getId());
        JSONObject resp = new JSONObject();
        if (easybuyUserAddressMapper.selectCount(queryWrapper) != 1){
            resp.put("result","error");
            resp.put("message","地址不存在");
        } else {
            EasybuyUserAddress userAddress = new EasybuyUserAddress(id,user.getId(),address,new Date(),isdefault,remark);
            easybuyUserAddressMapper.updateById(userAddress);
            resp.put("result","success");
        }
        return resp;
    }
}
