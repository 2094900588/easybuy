package com.easybuy.backend.controller.manage;

import com.alibaba.fastjson.JSONObject;
import com.easybuy.backend.service.prod.GetProdListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/manage/prod/")
public class ProdManageCountroller {
    @Autowired
    private GetProdListService getProdListService;
    @GetMapping("/getlist")
    public JSONObject getlist(@RequestParam Map<String, String> data) {
        int page = 1;
        int pagesize = 10;
        if(data.get("page") !=null)
            page = Integer.parseInt(data.get("page"));
        if(data.get("pagesize")!=null)
            pagesize = Integer.parseInt(data.get("pagesize"));
        return getProdListService.getlist(page,pagesize);
    }
}
