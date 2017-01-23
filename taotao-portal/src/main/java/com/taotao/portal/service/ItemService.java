package com.taotao.portal.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

/**
 * Created by Administrator on 2016/12/20 0020.
 */
public interface ItemService {

    TbItem getItemById(Long itemId);
    String getItemDescById(Long itemId);
    String getItemParam(Long itemId);
}
