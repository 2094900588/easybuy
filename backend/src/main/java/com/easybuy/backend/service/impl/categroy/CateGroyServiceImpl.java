package com.easybuy.backend.service.impl.categroy;

import com.alibaba.fastjson.JSONObject;
import com.easybuy.backend.mapper.EasybuyProductCategoryMapper;
import com.easybuy.backend.pojo.EasybuyProductCategory;
import com.easybuy.backend.service.categroy.CateGroyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CateGroyServiceImpl implements CateGroyService {

    @Autowired
    private EasybuyProductCategoryMapper easybuyProductCategoryMapper;

    @Override
    public JSONObject add(String name, Integer parentId, Integer type) {
        EasybuyProductCategory category=new EasybuyProductCategory(null,name,parentId,type,null);
        easybuyProductCategoryMapper.insert(category);
        JSONObject resp = new JSONObject();
        resp.put("result","success");
        return resp;
    }

    @Override
    public JSONObject del(Integer id) {
        easybuyProductCategoryMapper.deleteById(id);
        JSONObject resp = new JSONObject();
        resp.put("result","success");
        return resp;
    }

    @Override
    public JSONObject mod(Integer id, String name, Integer parentId, Integer type) {
        EasybuyProductCategory category=new EasybuyProductCategory(id,name,parentId,type,null);
        easybuyProductCategoryMapper.updateById(category);
        JSONObject resp = new JSONObject();
        resp.put("result","success");
        return resp;
    }
}
