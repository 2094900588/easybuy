package com.easybuy.backend.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.easybuy.backend.service.order.GetOrderDetailService;
import com.easybuy.backend.service.order.GetUserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user/")
public class UserOrderCountroller {

    @Autowired
    private GetUserOrderService getUserOrderService;

    @Autowired
    private GetOrderDetailService getOrderDetailService;

    @GetMapping("/getorder")
    public JSONObject login(){
        return getUserOrderService.getuserorder();
    }

    @GetMapping("/getorderdetail")
    public JSONObject deleteuser(@RequestParam Map<String, String> data) {
        Integer orderid = Integer.parseInt(data.get("orderid"));
        return getOrderDetailService.getorderdetail(orderid);
    }
}
