package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<TbItemCat> getItemCatList(Long parentId) {

		TbItemCatExample example = new TbItemCatExample();
		// 设置查询条件
		TbItemCatExample.Criteria criteria = example.createCriteria();
		// 根据parentid查询子节点
		criteria.andParentIdEqualTo(parentId);
		// 返回子节点列表
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		return list;
	}

}
