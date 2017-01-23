package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * Created by Administrator on 2016/12/18 0018.
 */
public interface RedisService {

    TaotaoResult syncContent(long contentid);
}
