package com.easybuy.backend.service.impl.order;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easybuy.backend.mapper.EasybuyOrderMapper;
import com.easybuy.backend.pojo.EasybuyOrder;
import com.easybuy.backend.pojo.EasybuyUser;
import com.easybuy.backend.service.impl.utils.UserDetailsImpl;
import com.easybuy.backend.service.order.GetUserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserOrderServiceImpl implements GetUserOrderService {

    @Autowired
    private EasybuyOrderMapper orderMapper;

    @Override
    public JSONObject getuserorder() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        EasybuyUser user = loginUser.getUser();
        QueryWrapper<EasybuyOrder> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("userid",user.getId());
        List<EasybuyOrder> orders =orderMapper.selectList(orderQueryWrapper);
        JSONObject resp = new JSONObject();
        resp.put("result","success");
        resp.put("userorder",orders);

        return resp;
    }
}
