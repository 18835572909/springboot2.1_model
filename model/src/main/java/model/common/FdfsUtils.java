package model.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

@Component
public class FdfsUtils {
	
	@Autowired
	private FastFileStorageClient storageClient;
	@Autowired
	private FdfsWebServer fdfsWebServer;
	@Autowired
	private ThumbImageConfig thumbImageConfig;
	

	/**
	 * 上传文件
	 * 
	 * @param file
	 *            文件对象
	 * @return 文件访问地址
	 * @throws IOException
	 */
	public String uploadFile(MultipartFile file) throws IOException {
		StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
				FilenameUtils.getExtension(file.getOriginalFilename()), null);
		/*
		 * 伸缩图，需要额外nginx配置： image模块
		 */
		String thumbPath = fdfsWebServer.getWebServerUrl() + thumbImageConfig.getThumbImagePath(storePath.getPath());
		System.out.println("伸缩图路径："+thumbPath);
		return getResAccessUrl(storePath);
	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 *            文件对象
	 * @return 文件访问地址
	 * @throws IOException
	 */
	public String uploadFile(File file) throws IOException {
		FileInputStream inputStream = new FileInputStream(file);
		StorePath storePath = storageClient.uploadFile(inputStream, file.length(),
				FilenameUtils.getExtension(file.getName()), null);
		return getResAccessUrl(storePath);
	}

	/**
	 * 将一段字符串生成一个文件上传
	 * 
	 * @param content
	 *            文件内容
	 * @param fileExtension
	 * @return
	 */
	public String uploadFile(String content, String fileExtension) {
		byte[] buff = content.getBytes(Charset.forName("UTF-8"));
		ByteArrayInputStream stream = new ByteArrayInputStream(buff);
		StorePath storePath = storageClient.uploadFile(stream, buff.length, fileExtension, null);
		return getResAccessUrl(storePath);
	}

	// 封装图片完整URL地址
	private String getResAccessUrl(StorePath storePath) {
		String fileUrl = fdfsWebServer.getWebServerUrl() + storePath.getFullPath();
		return fileUrl;
	}

	/**
	 * 下载文件
	 * 
	 * @param fileUrl
	 *            文件url: 以组名开始 group1/M00/00/00/wKjRZF0LQLqAcgZRAALxB5xHOIQ222.jpg
	 * @return
	 */
	public byte[] download(String fileUrl) {
		String group = fileUrl.substring(0, fileUrl.indexOf("/"));
		String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
		byte[] bytes = storageClient.downloadFile(group, path, new DownloadByteArray());
		return bytes;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileUrl
	 *            文件访问地址
	 * @return
	 */
	public void deleteFile(String fileUrl) {
		if (StringUtils.isEmpty(fileUrl)) {
			return;
		}
		try {
			StorePath storePath = StorePath.parseFromUrl(fileUrl);
			storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
		} catch (FdfsUnsupportStorePathException e) {
			System.out.println(e.getMessage());
		}
	}
}
