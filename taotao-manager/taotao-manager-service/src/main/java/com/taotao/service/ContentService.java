package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12 0012.
 */
public interface ContentService {

    public EUDataGridResult getContentList(int page, int rows,long categoryId);
    public TaotaoResult insertContent(TbContent content);
}
