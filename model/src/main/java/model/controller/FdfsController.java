package model.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import model.common.FdfsUtils;

@Controller
@RequestMapping("/fdfs")
public class FdfsController {

	@Autowired
	private FdfsUtils fdfsUtils;
	
	@ResponseBody
	@RequestMapping("/upload")
	public String upload(MultipartFile file) {
		try {
			return fdfsUtils.uploadFile(file);
		} catch (IOException e) {
			return "Upload Fail!";
		}
	}
	
	 /**
     * 文件下载
     * @param fileUrl  url 开头从组名开始
     * @param response
     * @throws Exception
     */
    @RequestMapping("/download")
    public void  download(String fileUrl, HttpServletResponse response) throws Exception{
    	System.out.println("fileUrl:"+fileUrl);
		byte[] data = fdfsUtils.download(fileUrl);
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("test.jpg", "UTF-8"));
		// 写出
		ServletOutputStream outputStream = response.getOutputStream();
		IOUtils.write(data, outputStream);
    }
    
	@ResponseBody
	@RequestMapping("/ping")
	public String ping() {
		return "pong";
	}
}
