package com.taotao.order.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.order.pojo.Order;
import com.taotao.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/12/23 0023.
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createOrder(@RequestBody Order order) {
        try {
            TaotaoResult result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping("/info/{orderId}")
    @ResponseBody
    public TaotaoResult getOrder(@PathVariable String orderId) {

        TaotaoResult result = orderService.getOrderByOrderId(orderId);
        return result;

    }

    @RequestMapping("/list/{userId}/{page}/{count}")
    @ResponseBody
    public TaotaoResult getOrder(@PathVariable Long userId, @PathVariable Integer page, @PathVariable Integer count) {

        TaotaoResult result = orderService.getOrderByUserId(userId, page, count);
        return result;

    }

    @RequestMapping("/changeStatus")
    @ResponseBody
    public TaotaoResult changeStatus(@RequestBody Order order) {

        try {
            TaotaoResult result = orderService.changeStatus(order.getOrderId(), order.getStatus(), order.getPaymentTime());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }


}

