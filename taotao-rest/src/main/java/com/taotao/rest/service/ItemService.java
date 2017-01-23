package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * Created by Administrator on 2016/12/20 0020.
 */
public interface ItemService {

    TaotaoResult getItemBaseInfo(long itemId);
    TaotaoResult getItemDesc(long itemId);
    TaotaoResult getItemParam(long itemId);
}
