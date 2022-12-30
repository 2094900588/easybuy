package com.easybuy.backend.service.impl.order;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easybuy.backend.mapper.EasybuyOrderMapper;
import com.easybuy.backend.pojo.EasybuyOrder;
import com.easybuy.backend.pojo.EasybuyProductCategory;
import com.easybuy.backend.service.order.GetOrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetOrderListServiceImpl implements GetOrderListService {
    @Autowired
    private EasybuyOrderMapper orderMapper;
    @Override
    public JSONObject getlist(Integer page, Integer pagesize) {
        if (page == null) page = 1;
        if(pagesize == null) pagesize = 10;
        IPage<EasybuyOrder> iPage = new Page<>(page,pagesize);
        List<EasybuyOrder> orders = orderMapper.selectPage(iPage,null).getRecords();
        JSONObject resp = new JSONObject();
        resp.put("result","success");
        resp.put("orders",orders);
        resp.put("pagesize",pagesize);
        resp.put("count",orderMapper.selectCount(null));
        return resp;
    }
}
