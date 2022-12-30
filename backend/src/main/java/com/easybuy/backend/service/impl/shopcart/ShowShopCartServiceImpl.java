package com.easybuy.backend.service.impl.shopcart;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.easybuy.backend.mapper.EasybuyProductMapper;
import com.easybuy.backend.pojo.EasybuyProduct;
import com.easybuy.backend.pojo.EasybuyUser;
import com.easybuy.backend.service.impl.utils.UserDetailsImpl;
import com.easybuy.backend.service.shopcart.ShowShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ShowShopCartServiceImpl implements ShowShopCartService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private EasybuyProductMapper easybuyProductMapper;
    @Override
    public JSONObject showcart() {
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
            EasybuyProduct easybuyProduct = easybuyProductMapper.
                    selectById(cartlist.getJSONObject(i).getInteger("productId"));
            cartlist.getJSONObject(i).put("price",easybuyProduct.getPrice());
            cartlist.getJSONObject(i).put("name",easybuyProduct.getName());
        }

        JSONObject resp = new JSONObject();
        resp.put("result","success");
        resp.put("cart",cartlist);
        return resp;
    }
}
