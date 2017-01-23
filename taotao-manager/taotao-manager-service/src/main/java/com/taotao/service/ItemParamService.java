package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
public interface ItemParamService {

    public TaotaoResult getItemParamByCid(long cid);
    public EUDataGridResult getItemParamlist(int page, int rows);
    public TaotaoResult insertItemParam(TbItemParam itemParam);
}
