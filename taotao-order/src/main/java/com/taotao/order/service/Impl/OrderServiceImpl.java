package com.taotao.order.service.Impl;

import com.github.pagehelper.PageHelper;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.dao.JedisClient;
import com.taotao.order.pojo.Order;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/12/23 0023.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbOrderMapper orderMapper;
    @Autowired
    private TbOrderItemMapper orderItemMapper;
    @Autowired
    private TbOrderShippingMapper orderShippingMapper;
    @Autowired
    private JedisClient jedisClient;

    @Value("${ORDER_GEN_KEY}")
    private String ORDER_GEN_KEY;
    @Value("${ORDER_INIT_ID}")
    private String ORDER_INIT_ID;
    @Value("${ORDER_DETAIL_GEN_KEY}")
    private String ORDER_DETAIL_GEN_KEY;


    @Override
    public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) {
        //向订单表中插入记录
        //获得订单号
        String string = jedisClient.get(ORDER_GEN_KEY);
        if (StringUtils.isBlank(string)) {
            jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
        }
        long orderId = jedisClient.incr(ORDER_GEN_KEY);
        //补全pojo的属性
        order.setOrderId(orderId + "");
        //状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        order.setStatus(1);
        Date date = new Date();
        order.setCreateTime(date);
        order.setUpdateTime(date);
        //0：未评价 1：已评价
        order.setBuyerRate(0);
        //向订单表插入数据
        orderMapper.insert(order);
        //插入订单明细
        for (TbOrderItem tbOrderItem : itemList) {
            //补全订单明细
            //取订单明细id
            long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
            tbOrderItem.setId(orderDetailId + "");
            tbOrderItem.setOrderId(orderId + "");
            //向订单明细插入记录
            orderItemMapper.insert(tbOrderItem);
        }
        //插入物流表
        //补全物流表的属性
        orderShipping.setOrderId(orderId + "");
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        orderShippingMapper.insert(orderShipping);

        return TaotaoResult.ok(orderId);
    }

    @Override
    public TaotaoResult getOrderByOrderId(String orderId) {
        TbOrder tbOrder = orderMapper.selectByPrimaryKey(orderId);

        TbOrderItemExample orderItemExample = new TbOrderItemExample();
        TbOrderItemExample.Criteria criteria = orderItemExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<TbOrderItem> orderItems = orderItemMapper.selectByExample(orderItemExample);

        TbOrderShippingExample orderShippingExample = new TbOrderShippingExample();
        TbOrderShippingExample.Criteria criteria1 = orderShippingExample.createCriteria();
        criteria1.andOrderIdEqualTo(orderId);
        List<TbOrderShipping> list = orderShippingMapper.selectByExample(orderShippingExample);
        TbOrderShipping orderShipping = list.get(0);


        Order order = new Order();
        order.setOrderId(tbOrder.getOrderId());
        order.setPayment(tbOrder.getPayment());
        order.setPaymentType(tbOrder.getPaymentType());
        order.setStatus(tbOrder.getStatus());
        order.setCreateTime(tbOrder.getCreateTime());
        order.setPostFee(tbOrder.getPostFee());
        order.setUserId(tbOrder.getUserId());
        order.setBuyerMessage(tbOrder.getBuyerMessage());
        order.setBuyerNick(tbOrder.getBuyerNick());
        order.setOrderItems(orderItems);
        order.setOrderShipping(orderShipping);

        return TaotaoResult.ok(order);
    }

    @Override
    public TaotaoResult getOrderByUserId(long userId, int page, int count) {

        TbOrderExample orderExample = new TbOrderExample();
        TbOrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andUserIdEqualTo(userId);

        PageHelper.startPage(page, count);
        List<TbOrder> orderList = orderMapper.selectByExample(orderExample);
        return TaotaoResult.ok(orderList);

    }

    @Override
    public TaotaoResult changeStatus(String orderId, int status, Date paymentTime) {

        TbOrder order = orderMapper.selectByPrimaryKey(orderId);
        order.setStatus(status);
        order.setPaymentTime(paymentTime);
        orderMapper.updateByPrimaryKey(order);

        return TaotaoResult.ok();
    }
}
