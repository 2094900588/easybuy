package com.easybuy.backend.service.impl.user;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easybuy.backend.mapper.EasybuyUserMapper;
import com.easybuy.backend.pojo.EasybuyUser;
import com.easybuy.backend.service.user.GetUserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GetUserListServiceImpl implements GetUserListService {

    @Autowired
    EasybuyUserMapper userMapper;

    @Override
    public JSONObject getuserlist(Integer page, Integer pagesize) {
        if (page == null) page = 1;
        if(pagesize == null) pagesize = 10;
        IPage<EasybuyUser> userIPage = new Page<>(page,pagesize);
        List<EasybuyUser> users = userMapper.selectPage(userIPage,null).getRecords();
        JSONObject resp = new JSONObject();
        for (EasybuyUser user:users)
            user.setPassword("");
        resp.put("result","success");
        resp.put("users",users);
        resp.put("pagesize",pagesize);
        resp.put("count",userMapper.selectCount(null));
        return resp;
    }
}
