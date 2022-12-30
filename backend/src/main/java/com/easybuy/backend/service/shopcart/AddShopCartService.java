package com.easybuy.backend.service.shopcart;

import com.alibaba.fastjson.JSONObject;

public interface AddShopCartService {

    JSONObject add(Integer productId,Integer quantity);
}
