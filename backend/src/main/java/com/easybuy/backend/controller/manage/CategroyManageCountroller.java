package com.easybuy.backend.controller.manage;

import com.alibaba.fastjson.JSONObject;
import com.easybuy.backend.service.categroy.CateGroyService;
import com.easybuy.backend.service.categroy.GetCategroyListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/manage/categroy/")
public class CategroyManageCountroller {

    @Autowired
    private GetCategroyListService getCategroyListService;
    @Autowired
    private CateGroyService cateGroyService;

    @GetMapping("/getlist")
    public JSONObject getlist(@RequestParam Map<String, String> data) {
        int page = 1;
        int pagesize = 10;
        if(data.get("page") !=null)
            page = Integer.parseInt(data.get("page"));
        if(data.get("pagesize")!=null)
            pagesize = Integer.parseInt(data.get("pagesize"));
        return getCategroyListService.getcategroylist(page,pagesize);
    }

    @GetMapping("/delete")
    public JSONObject del(@RequestParam Map<String, String> data) {
        int id = Integer.parseInt(data.get("id"));
        return cateGroyService.del(id);
    }

    @PostMapping("/add")
    public JSONObject add(@RequestBody JSONObject map){
        String name = map.getString("name");
        Integer parentId = map.getInteger("parentId");
        Integer type = map.getInteger("type");
        return cateGroyService.add(name,parentId,type);
    }

    @PostMapping("/modify")
    public JSONObject mod(@RequestBody JSONObject map){
        Integer id = map.getInteger("id");
        String name = map.getString("name");
        Integer parentId = map.getInteger("parentId");
        Integer type = map.getInteger("type");
        return cateGroyService.mod(id,name,parentId,type);
    }

}
