package com.taotao.rest.service;

import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12 0012.
 */
public interface ContentService {

    public List<TbContent> getContentList(long contentCid);
}
