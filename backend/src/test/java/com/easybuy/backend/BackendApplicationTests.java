package com.easybuy.backend;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


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
        JSONArray jsonArray = new JSONArray();
        JSONObject json = new JSONObject();
        json.put("productId",123);
        json.put("quantity",123);
        jsonArray.add(json);
//
        ;
        jsonArray = JSONArray.parseArray(redisTemplate.opsForValue().get("user-1-cart"));
        jsonArray.getJSONObject(0).put("1234",123);
        redisTemplate.opsForValue().set("user-1-cart",jsonArray.toString());
        System.out.println();
        System.out.println(jsonArray);

    }

}
