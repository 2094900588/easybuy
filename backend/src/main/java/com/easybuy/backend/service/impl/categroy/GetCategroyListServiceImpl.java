package com.easybuy.backend.service.impl.categroy;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easybuy.backend.mapper.EasybuyProductCategoryMapper;
import com.easybuy.backend.pojo.EasybuyProductCategory;
import com.easybuy.backend.pojo.EasybuyUser;
import com.easybuy.backend.service.categroy.GetCategroyListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCategroyListServiceImpl implements GetCategroyListService {

    @Autowired
    EasybuyProductCategoryMapper productCategoryMapper;

    @Override
    public JSONObject getcategroylist(Integer page, Integer pagesize) {
        if (page == null) page = 1;
        if(pagesize == null) pagesize = 10;
        IPage<EasybuyProductCategory> iPage = new Page<>(page,pagesize);
        List<EasybuyProductCategory> productCategories = productCategoryMapper.selectPage(iPage,null).getRecords();
        JSONObject resp = new JSONObject();
        resp.put("result","success");
        resp.put("categroy",productCategories);
        resp.put("pagesize",pagesize);
        resp.put("count",productCategoryMapper.selectCount(null));
        return resp;
    }
}
