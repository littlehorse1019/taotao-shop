package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12 0012.
 */
public interface ContentCategoryService {

    public List<EUTreeNode> getCategoryList(long parentId);
    public TaotaoResult insertContentCategory(long parentId, String name);
    public TaotaoResult deleteContentCategory(long parentId,long id);
    public TaotaoResult updateContentCategory(long parentId,String name);


}
