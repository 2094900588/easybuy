package com.easybuy.backend.controller.manage;

import com.alibaba.fastjson.JSONObject;
import com.easybuy.backend.service.prod.GetProdListService;
import com.easybuy.backend.service.prod.ProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/manage/prod/")
public class ProdManageCountroller {
    @Autowired
    private GetProdListService getProdListService;
    @Autowired
    private ProdService prodService;
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
    @GetMapping("/delete")
    public JSONObject del(@RequestParam Map<String, String> data) {
        int id = Integer.parseInt(data.get("id"));
        return prodService.del(id);
    }
//String name, String description, Float price, Integer stock, Integer categoryLevel1Id,
// Integer categoryLevel2Id, Integer categoryLevel3Id, String fileName, Integer isDelete

    @PostMapping("/add")
    public JSONObject add(@RequestBody JSONObject map){
        String name = map.getString("name");
        String description = map.getString("description");
        Float price = map.getFloat("price");
        Integer stock = map.getInteger("stock");
        Integer categoryLevel1Id = map.getInteger("categoryLevel1Id");
        Integer categoryLevel2Id = map.getInteger("categoryLevel2Id");
        Integer categoryLevel3Id = map.getInteger("categoryLevel3Id");
        String fileName = map.getString("fileName");
        Integer isDelete = map.getInteger("isDelete");
        return prodService.add(name,description,price,stock,categoryLevel1Id,categoryLevel2Id,categoryLevel3Id,fileName,isDelete);
    }

    @PostMapping("/modify")
    public JSONObject mod(@RequestBody JSONObject map){
        Integer id  = map.getInteger("id");
        String name = map.getString("name");
        String description = map.getString("description");
        Float price = map.getFloat("price");
        Integer stock = map.getInteger("stock");
        Integer categoryLevel1Id = map.getInteger("categoryLevel1Id");
        Integer categoryLevel2Id = map.getInteger("categoryLevel2Id");
        Integer categoryLevel3Id = map.getInteger("categoryLevel3Id");
        String fileName = map.getString("fileName");
        Integer isDelete = map.getInteger("isDelete");
        return prodService.mod(id,name,description,price,stock,categoryLevel1Id,categoryLevel2Id,categoryLevel3Id,fileName,isDelete);
    }
}
