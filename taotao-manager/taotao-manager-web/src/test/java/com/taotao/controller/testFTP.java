package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 * Created by Administrator on 2016/12/8 0008.
 */
public class testFTP {

	public static void main(String[] args) throws Exception {
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect("192.168.56.101", 21);
		ftpClient.login("ftpuser", "ftpuser");
		FileInputStream inputStream = new FileInputStream(new File("E:\\Pictures\\QQ.jpg"));
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		ftpClient.storeFile("32.jpg", inputStream);
		System.out.println(ftpClient.getReplyCode());

		inputStream.close();

		ftpClient.logout();
	}
}
