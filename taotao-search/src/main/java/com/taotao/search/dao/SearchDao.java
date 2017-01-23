package com.taotao.search.dao;

import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * Created by Administrator on 2016/12/19 0019.
 */
public interface SearchDao {

    SearchResult search(SolrQuery query) throws Exception;
}
