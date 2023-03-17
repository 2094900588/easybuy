package com.easybuy.backend.service.impl.prod;

import com.alibaba.fastjson.JSONObject;
import com.easybuy.backend.mapper.EasybuyProductMapper;
import com.easybuy.backend.pojo.EasybuyProduct;
import com.easybuy.backend.service.prod.ProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdServiceImpl implements ProdService {
    @Autowired
    private EasybuyProductMapper easybuyProductMapper;

    @Override
    public JSONObject add(String name, String description, Float price, Integer stock, Integer categoryLevel1Id, Integer categoryLevel2Id, Integer categoryLevel3Id, String fileName, Integer isDelete) {
        EasybuyProduct product = new EasybuyProduct(null,name,description,price,stock,
                categoryLevel1Id,categoryLevel2Id,categoryLevel3Id,fileName,isDelete);
        easybuyProductMapper.insert(product);
        JSONObject resp = new JSONObject();
        resp.put("result","success");
        return resp;
    }

    @Override
    public JSONObject del(Integer id) {
        easybuyProductMapper.deleteById(id);
        JSONObject resp = new JSONObject();
        resp.put("result","success");
        return resp;
    }

    @Override
    public JSONObject mod(Integer id, String name, String description, Float price, Integer stock, Integer categoryLevel1Id, Integer categoryLevel2Id, Integer categoryLevel3Id, String fileName, Integer isDelete) {
        EasybuyProduct product = new EasybuyProduct(id,name,description,price,stock,
                categoryLevel1Id,categoryLevel2Id,categoryLevel3Id,fileName,isDelete);
        easybuyProductMapper.updateById(product);
        JSONObject resp = new JSONObject();
        resp.put("result","success");
        return resp;
    }
}
