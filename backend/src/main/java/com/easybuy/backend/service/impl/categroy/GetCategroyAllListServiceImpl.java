package com.easybuy.backend.service.impl.categroy;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easybuy.backend.mapper.EasybuyProductCategoryMapper;
import com.easybuy.backend.pojo.EasybuyProductCategory;
import com.easybuy.backend.service.categroy.GetCategroyAllListService;
import com.easybuy.backend.service.categroy.GetCategroyListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCategroyAllListServiceImpl implements GetCategroyAllListService {

    @Autowired
    EasybuyProductCategoryMapper productCategoryMapper;

    @Override
    public JSONObject getalllist() {
        List<EasybuyProductCategory> productCategories = productCategoryMapper.selectList(null);
        JSONObject resp = new JSONObject();
        resp.put("result","success");
        resp.put("categroy",productCategories);
        resp.put("count",productCategoryMapper.selectCount(null));
        return resp;
    }
}
