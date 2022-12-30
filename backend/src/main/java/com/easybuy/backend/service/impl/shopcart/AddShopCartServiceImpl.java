package com.easybuy.backend.service.impl.shopcart;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.easybuy.backend.pojo.EasybuyUser;
import com.easybuy.backend.service.impl.utils.UserDetailsImpl;
import com.easybuy.backend.service.shopcart.AddShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AddShopCartServiceImpl implements AddShopCartService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public JSONObject add(Integer productId, Integer quantity) {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext()
                        .getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        EasybuyUser user = loginUser.getUser();
        String cartkey = "usercart-" + user.getId().toString();
        JSONArray cartlist = null;
        boolean flag = false;// 是否查到
        if(Boolean.FALSE.equals(redisTemplate.hasKey(cartkey))){
            cartlist = new JSONArray();
        } else {
            cartlist = JSONArray.parseArray(redisTemplate.opsForValue().get(cartkey));
        }
        for(int i = 0;i<cartlist.size();i++){
            if (cartlist.getJSONObject(i).getInteger("productId").equals(productId)){
                flag = true;
                if (quantity <= 0){
                    cartlist.remove(i);
                    continue;
                }
                cartlist.getJSONObject(i).put("quantity",quantity);
            }
        }
        if (!flag){
            JSONObject item = new JSONObject();
            item.put("productId",productId);
            item.put("quantity",quantity);
            cartlist.add(item);
        }
        redisTemplate.opsForValue().set(cartkey,cartlist.toString());
        JSONObject resp = new JSONObject();
        resp.put("result","success");
        return resp;
    }
}
