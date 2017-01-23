package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/12/22 0022.
 */
public interface UserService {

    TaotaoResult checkData(String content, Integer type);
    TaotaoResult createUser(TbUser user);
    TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
    TaotaoResult getUserByToken(String token);
    TaotaoResult logOut(String token);
}
