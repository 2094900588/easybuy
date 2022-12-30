package com.easybuy.backend.controller.prod;

import com.alibaba.fastjson.JSONObject;
import com.easybuy.backend.service.prod.GetlistProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/prod/")

public class ProdCountroller {

    @Autowired
    private GetlistProdService prodService;

    @GetMapping("/list")
    public JSONObject getlist(@RequestParam Map<String, String> data) {
        int page = 1;
        int pagesize = 10;
        if(data.get("page") !=null)
            page = Integer.parseInt(data.get("page"));
        if(data.get("pagesize")!=null)
            pagesize = Integer.parseInt(data.get("pagesize"));
        return prodService.getlist(page,pagesize);
    }
}
