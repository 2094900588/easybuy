package com.easybuy.backend.controller.categroy;


import com.alibaba.fastjson.JSONObject;
import com.easybuy.backend.service.categroy.GetCategroyAllListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/categroy/")
public class CategroyCountroller {
    @Autowired
    private GetCategroyAllListService getCategroyAllListService;

    @GetMapping("/getalllist")
    public JSONObject getlist() {
        return getCategroyAllListService.getalllist();
    }

}
