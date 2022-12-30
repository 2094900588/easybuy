package com.easybuy.backend.service.impl.news;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easybuy.backend.mapper.EasyBuyNewsMapper;
import com.easybuy.backend.pojo.EasybuyNews;
import com.easybuy.backend.pojo.EasybuyProductCategory;
import com.easybuy.backend.service.news.GetNewsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetNewsListServiceImpl implements GetNewsListService {

    @Autowired
    private EasyBuyNewsMapper newsMapper;
    @Override
    public JSONObject getlist(Integer page, Integer pagesize) {
        if (page == null) page = 1;
        if(pagesize == null) pagesize = 10;
        IPage<EasybuyNews> iPage = new Page<>(page,pagesize);
        List<EasybuyNews> news = newsMapper.selectPage(iPage,null).getRecords();
        JSONObject resp = new JSONObject();
        resp.put("result","success");
        resp.put("news",news);
        resp.put("pagesize",pagesize);
        resp.put("count",newsMapper.selectCount(null));
        return resp;
    }
}
