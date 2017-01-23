package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

/**
 * Created by Administrator on 2016/12/6 0006.
 */
public interface ItemService {
	public TbItem getItemById(long itemId);
	public EUDataGridResult getItemList(int page, int rows);
	public TaotaoResult createItem(TbItem item, String desc,String itemParams) throws Exception;
}
