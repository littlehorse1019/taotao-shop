package com.taotao.service;

import java.util.List;

import com.taotao.pojo.TbItemCat;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
public interface ItemCatService {

	public List<TbItemCat> getItemCatList(Long parentId);
}
