package com.taotao.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;

/**
 * Created by Administrator on 2016/12/8 0008.
 */
@Service
public class PictureServiceImpl implements PictureService {

	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	@Value("${FILE_UPLOAD_PATH}")
	private String FILE_UPLOAD_PATH;
	@Value("${FTP_SERVER_IP}")
	private String FTP_SERVER_IP;
	@Value("${FTP_SERVER_PORT}")
	private Integer FTP_SERVER_PORT;
	@Value("${FTP_SERVER_USERNAME}")
	private String FTP_SERVER_USERNAME;
	@Value("${FTP_SERVER_PASSWORD}")
	private String FTP_SERVER_PASSWORD;

	@Override
	public PictureResult uploadFile(MultipartFile uploadFile) throws Exception {

		// 上传文件功能实现
		String path = savePicture(uploadFile);
		// 回显
		PictureResult result = new PictureResult(0, IMAGE_BASE_URL + path);
		return result;
	}

	private String savePicture(MultipartFile uploadFile) {
		String result = null;
		try {
			// 上传文件功能实现
			// 判断文件是否为空
			if (uploadFile.isEmpty())
				return null;
			// 上传文件以日期为单位分开存放，可以提高图片的查询速度
			String filePath = new DateTime().toString("/yyyy/MM/dd");

			// 取原始文件名
			String originalFilename = uploadFile.getOriginalFilename();
			// 新文件名
			String newFileName = IDUtils.genImageName() + originalFilename.substring(originalFilename.lastIndexOf("."));
			// 转存文件，上传到ftp服务器
			FtpUtil.uploadFile(FTP_SERVER_IP, FTP_SERVER_PORT, FTP_SERVER_USERNAME, FTP_SERVER_PASSWORD,
					FILE_UPLOAD_PATH, filePath, newFileName, uploadFile.getInputStream());
			result = filePath + "/" + newFileName;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
