package com.taotao.portal.service;

import com.taotao.pojo.TbUser;

/**
 * Created by Administrator on 2016/12/22 0022.
 */
public interface UserService {
    TbUser getUserByToken(String token);
}
