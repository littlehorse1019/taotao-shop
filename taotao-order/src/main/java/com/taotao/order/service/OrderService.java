package com.taotao.order.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/12/23 0023.
 */
public interface OrderService {

    TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
    TaotaoResult getOrderByOrderId(String orderId);
    TaotaoResult getOrderByUserId(long userId,int page,int count);
    TaotaoResult changeStatus(String orderId, int status, Date paymentTime);
}
