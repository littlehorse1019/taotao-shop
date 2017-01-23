package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

/**
 * Created by Administrator on 2016/12/20 0020.
 */
public interface SearchService {

    SearchResult search(String queryString, int page);
}
