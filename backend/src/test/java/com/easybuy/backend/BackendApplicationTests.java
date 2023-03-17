package com.easybuy.backend;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easybuy.backend.mapper.EasybuyProductMapper;
import com.easybuy.backend.pojo.EasybuyProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private EasybuyProductMapper productMapper;

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
    @Test
    void contextLoads() {
//        JSONObject resp = new JSONObject();
//        List<Map<String,String>> list = new ArrayList<>();
//        Map<String,String> map = new HashMap<>();
//        map.put("123","456");
//        list.add(map);
//        resp.put("list",list);
//        resp.getJSONArray("list").add(0,new Object[]{"124","555"});
////        resp.getJSONArray("list").getJSONObject(0).put();
//        System.out.println(resp);
//        JSONArray jsonArray = new JSONArray();
//        JSONObject json = new JSONObject();
//        json.put("productId",123);
//        json.put("quantity",123);
//        jsonArray.add(json);
////
//        ;
//        jsonArray = JSONArray.parseArray(redisTemplate.opsForValue().get("user-1-cart"));
//        jsonArray.getJSONObject(0).put("1234",123);
//        redisTemplate.opsForValue().set("user-1-cart",jsonArray.toString());
//        System.out.println();
//        System.out.println(jsonArray);
//        QueryWrapper<EasybuyProduct> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("name","%面%");
//        System.out.println(productMapper.selectList(queryWrapper));
        System.out.println(getUUID());
    }

}
