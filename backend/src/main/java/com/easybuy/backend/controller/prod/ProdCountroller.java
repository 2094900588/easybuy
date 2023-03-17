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
        String keyword = null;
        Integer level1 = null;
        Integer level2 = null;
        Integer level3 = null;
        if(data.get("keyword")!=null)
            keyword = data.get("keyword");
        if(data.get("level1")!=null)
            level1 = Integer.parseInt(data.get("level1"));
        if(data.get("level2")!=null)
            level2 = Integer.parseInt(data.get("level2"));
        if(data.get("level3")!=null)
            level3 = Integer.parseInt(data.get("level3"));
        return prodService.getlist(keyword,level1,level2,level3);
    }
}
