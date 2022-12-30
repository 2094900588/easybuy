package com.easybuy.backend.service.impl.user;

import com.alibaba.fastjson.JSONObject;
import com.easybuy.backend.mapper.EasybuyUserMapper;
import com.easybuy.backend.pojo.EasybuyUser;
import com.easybuy.backend.service.user.DeleteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserServiceImpl implements DeleteUserService {

    @Autowired
    private EasybuyUserMapper userMapper;

    @Override
    public JSONObject deleteuser(Integer userid) {
        JSONObject resp = new JSONObject();
        resp.put("result","error");
        if (userid == null){
            resp.put("message","用户id不存在");
            return resp;
        }
        userMapper.deleteById(userid);
        resp.put("result","success");

        return resp;
    }
}
