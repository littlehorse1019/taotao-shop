package com.taotao.portal.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2016/12/23 0023.
 */
public interface CartService {
    TaotaoResult addCartItem(long itemId, int num,HttpServletRequest request, HttpServletResponse response);
    List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
    TaotaoResult updateCartItemNum(long itemId,int num,HttpServletRequest request, HttpServletResponse response);
    TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
}
