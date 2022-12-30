package com.easybuy.backend.service.impl.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easybuy.backend.mapper.EasybuyOrderDetailMapper;
import com.easybuy.backend.mapper.EasybuyOrderMapper;
import com.easybuy.backend.mapper.EasybuyProductMapper;
import com.easybuy.backend.pojo.EasybuyOrder;
import com.easybuy.backend.pojo.EasybuyOrderDetail;
import com.easybuy.backend.pojo.EasybuyProduct;
import com.easybuy.backend.pojo.EasybuyUser;
import com.easybuy.backend.service.impl.utils.UserDetailsImpl;
import com.easybuy.backend.service.order.GetOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetOrderDetailServiceImpl implements GetOrderDetailService {
    @Autowired
    private EasybuyOrderDetailMapper orderDetailMapper;
    @Autowired
    private EasybuyProductMapper easybuyProductMapper;
    @Autowired
    private EasybuyOrderMapper orderMapper;

    @Override
    public JSONObject getorderdetail(Integer orderid) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        EasybuyUser user = loginUser.getUser();
        QueryWrapper<EasybuyOrder> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("userid",user.getId());
        orderQueryWrapper.eq("id",orderid);
        JSONObject resp = new JSONObject();
        resp.put("result","error");
        if(orderMapper.selectCount(orderQueryWrapper) < 1){
            resp.put("message","订单不存在");
            return resp;
        }
        QueryWrapper<EasybuyOrderDetail> orderDetailQueryWrapper = new QueryWrapper<>();
        orderDetailQueryWrapper.eq("orderid",orderid);
        List<EasybuyOrderDetail> orderDetails = orderDetailMapper.selectList(orderDetailQueryWrapper);
        resp.put("result","success");
        JSONArray orderdetail = JSONArray.parseArray(JSON.toJSONString(orderDetails));
        for(int i=0;i<orderdetail.size();i++){
            EasybuyProduct product = easybuyProductMapper.selectById(orderDetails.get(i).getProductid());
            orderdetail.getJSONObject(i).put("productname",product == null?null:product.getName());
        }
        resp.put("orderdetail",orderdetail);
        return resp;
    }
}
