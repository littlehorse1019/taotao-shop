package com.taotao.portal.service.Impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/20 0020.
 */
@Service
public class ItemServiceImpl implements ItemService {


    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;
    @Value("${ITEM_DESC_URL}")
    private String ITEM_DESC_URL;
    @Value("${ITEM_PARAM_URL}")
    private String ITEM_PARAM_URL;


    @Override
    public ItemInfo getItemById(Long itemId) {

        try {
            //调用rest的服务查询商品基本信息
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
            if (!StringUtils.isBlank(json)) {
                TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, ItemInfo.class);
                if (taotaoResult.getStatus() == 200) {
                    ItemInfo item = (ItemInfo) taotaoResult.getData();
                    return item;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getItemDescById(Long itemId) {
        try {
            //查询商品描述
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + itemId);
            //转换成java对象
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemDesc.class);
            if (taotaoResult.getStatus() == 200) {
                TbItemDesc itemDesc = (TbItemDesc) taotaoResult.getData();
                //取商品描述信息
                String result = itemDesc.getItemDesc();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getItemParam(Long itemId) {
        try {
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + itemId);
            //把json转换成java对象
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
            if (taotaoResult.getStatus() == 200) {
                TbItemParamItem itemParamItem = (TbItemParamItem) taotaoResult.getData();
                String paramData = itemParamItem.getParamData();
                //生成html
                // 把规格参数json数据转换成java对象
                List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
                StringBuffer sb = new StringBuffer();
                sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
                sb.append("    <tbody>\n");
                for(Map m1:jsonList) {
                    sb.append("        <tr>\n");
                    sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
                    sb.append("        </tr>\n");
                    List<Map> list2 = (List<Map>) m1.get("params");
                    for(Map m2:list2) {
                        sb.append("        <tr>\n");
                        sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
                        sb.append("            <td>"+m2.get("v")+"</td>\n");
                        sb.append("        </tr>\n");
                    }
                }
                sb.append("    </tbody>\n");
                sb.append("</table>");
                //返回html片段
                return sb.toString();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}
