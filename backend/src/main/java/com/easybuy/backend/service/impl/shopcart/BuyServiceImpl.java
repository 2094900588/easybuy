package com.easybuy.backend.service.impl.shopcart;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easybuy.backend.mapper.EasybuyOrderDetailMapper;
import com.easybuy.backend.mapper.EasybuyOrderMapper;
import com.easybuy.backend.mapper.EasybuyProductMapper;
import com.easybuy.backend.mapper.EasybuyUserAddressMapper;
import com.easybuy.backend.pojo.*;
import com.easybuy.backend.service.impl.utils.UserDetailsImpl;
import com.easybuy.backend.service.shopcart.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BuyServiceImpl implements BuyService {
    @Autowired
    private EasybuyUserAddressMapper easybuyUserAddressMapper;
    @Autowired
    private EasybuyProductMapper easybuyProductMapper;
    @Autowired
    private EasybuyOrderMapper easybuyOrderMapper;
    @Autowired
    private EasybuyOrderDetailMapper easybuyOrderDetailMapper;

    @Override
    public JSONObject buy(JSONObject data) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext()
                        .getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        EasybuyUser user = loginUser.getUser();
        JSONArray cart = data.getJSONArray("cart");
        Integer addressid = data.getInteger("addressid");
        float cost = 0;
        String serialNumber = getUUID();

        QueryWrapper<EasybuyUserAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",user.getId());
        queryWrapper.eq("id",addressid);
        JSONObject resp = new JSONObject();
        if(easybuyUserAddressMapper.selectCount(queryWrapper) < 1){
            resp.put("result","error");
            resp.put("message","没有该地址！");
            return resp;
        }
        EasybuyUserAddress address = easybuyUserAddressMapper.selectById(addressid);
        for(int i=0;i<cart.size();i++){
            EasybuyProduct product = easybuyProductMapper.selectById(cart.getJSONObject(i).getInteger("productId"));
            cost += product.getPrice() * cart.getJSONObject(i).getInteger("quantity");
        }
        EasybuyOrder order = new EasybuyOrder(
                null, user.getId(), user.getLoginname(),address.getAddress(),new Date(),cost,serialNumber
        );
        easybuyOrderMapper.insert(order);
        QueryWrapper<EasybuyOrder> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("serialnumber",serialNumber);
        Integer orderid = easybuyOrderMapper.selectOne(queryWrapper1).getId();// 取id

        for(int i=0;i<cart.size();i++){ //插入详细表
            EasybuyProduct product = easybuyProductMapper.selectById(cart.getJSONObject(i).getInteger("productId"));
            EasybuyOrderDetail orderDetail = new EasybuyOrderDetail(
                    null,orderid,cart.getJSONObject(i).getInteger("productId"),
                    cart.getJSONObject(i).getInteger("quantity"),
                    product.getPrice() * cart.getJSONObject(i).getInteger("quantity")
                    );
            easybuyOrderDetailMapper.insert(orderDetail);
        }
        resp.put("result","success");

        return resp;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
}
