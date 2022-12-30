package com.easybuy.backend.service.impl.prod;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easybuy.backend.mapper.EasybuyProductMapper;
import com.easybuy.backend.pojo.EasybuyOrder;
import com.easybuy.backend.pojo.EasybuyProduct;
import com.easybuy.backend.service.prod.GetProdListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProdListServiceImpl implements GetProdListService {

    @Autowired
    private EasybuyProductMapper productMapper;
    @Override
    public JSONObject getlist(Integer page, Integer pagesize) {
        if (page == null) page = 1;
        if(pagesize == null) pagesize = 10;
        IPage<EasybuyProduct> iPage = new Page<>(page,pagesize);
        List<EasybuyProduct> products = productMapper.selectPage(iPage,null).getRecords();
        JSONObject resp = new JSONObject();
        resp.put("result","success");
        resp.put("prod",products);
        resp.put("pagesize",pagesize);
        resp.put("count",productMapper.selectCount(null));
        return resp;
    }
}
