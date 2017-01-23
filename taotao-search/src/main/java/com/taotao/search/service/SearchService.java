package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

/**
 * Created by Administrator on 2016/12/19 0019.
 */
public interface SearchService {

    SearchResult search(String queryString, int page, int rows) throws Exception;
}
