package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;

/**
 * Created by Administrator on 2016/12/8 0008.
 */
public interface PictureService {

	public PictureResult uploadFile(MultipartFile uploadFile) throws Exception;
}
