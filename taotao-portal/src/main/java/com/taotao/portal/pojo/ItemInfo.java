package com.taotao.portal.pojo;

import com.taotao.pojo.TbItem;

/**
 * Created by Administrator on 2016/12/20 0020.
 */
public class ItemInfo extends TbItem {

    public String[] getImages() {
        String image = getImage();
        if (image != null) {
            String[] images = image.split(",");
            return images;
        }
        return null;
    }
}