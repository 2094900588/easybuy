package com.easybuy.backend.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.easybuy.backend.service.shopcart.AddShopCartService;
import com.easybuy.backend.service.shopcart.ShowShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user/")
public class UserShopCartController {
    @Autowired
    private ShowShopCartService showShopCartService;
    @Autowired
    private AddShopCartService addShopCartService;

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

}
