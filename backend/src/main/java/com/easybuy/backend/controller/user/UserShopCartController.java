package com.easybuy.backend.controller.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.easybuy.backend.service.shopcart.AddShopCartService;
import com.easybuy.backend.service.shopcart.BuyService;
import com.easybuy.backend.service.shopcart.ShowShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/")
public class UserShopCartController {
    @Autowired
    private ShowShopCartService showShopCartService;
    @Autowired
    private AddShopCartService addShopCartService;
    @Autowired
    private BuyService buyService;

    @GetMapping("/getcart")
    public JSONObject getcart(){
        return showShopCartService.showcart();
    }

    @GetMapping("/addcart")
    public JSONObject addcart(@RequestParam Map<String, String> data){
        Integer productId = Integer.parseInt(data.get("productId"));
        Integer quantity = Integer.parseInt(data.get("quantity"));
        return addShopCartService.add(productId,quantity);
    }

    @PostMapping("/buy")
    public JSONObject buy(@RequestBody JSONObject map){
        return buyService.buy(map);
    }
}
