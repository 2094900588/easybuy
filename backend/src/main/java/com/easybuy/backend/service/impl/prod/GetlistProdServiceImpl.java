package com.easybuy.backend.service.impl.prod;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easybuy.backend.mapper.EasybuyProductMapper;
import com.easybuy.backend.pojo.EasybuyProduct;
import com.easybuy.backend.service.prod.GetlistProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetlistProdServiceImpl implements GetlistProdService {

    @Autowired
    private EasybuyProductMapper productMapper;
    @Override
    public JSONObject getlist(String keyword,Integer level1,Integer level2,Integer level3) {
        QueryWrapper<EasybuyProduct> queryWrapper = new QueryWrapper<>();
        if(keyword != null)
            queryWrapper.like("name","%"+keyword+"%");
        if(level1 != null)
            queryWrapper.eq("categoryLevel1Id",level1);
        if(level2 != null)
            queryWrapper.eq("categoryLevel2Id",level2);
        if(level3 != null)
            queryWrapper.eq("categoryLevel3Id",level3);
        List<EasybuyProduct> products = productMapper.selectList(queryWrapper);
        JSONObject resp = new JSONObject();
        resp.put("result","success");
        resp.put("prod",products);
        resp.put("count",productMapper.selectCount(null));
        return resp;
    }
}
