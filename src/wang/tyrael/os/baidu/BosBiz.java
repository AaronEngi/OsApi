package wang.tyrael.os.baidu;

import java.io.File;

import com.baidubce.services.bos.BosClient;

import cn.tyrael.library.file.FilePathParser;

/**
 * 为上层提供简单的接口
 */
public class BosBiz {
	public final BosClient bosClient = BosApi.getClient();
	
	/**
	 * 将本地文件上传到bos
	 * @param localPath
	 * @param bosDir 第一段作为bucket，方法内会自动分析并且拼上文件名
	 */
	public void putObject(String localPath, String bosDir){
		File file = new File(localPath);
		
		FilePathParser p = new FilePathParser(bosDir);
		String bucket = p.getFirstDir();
		
		String filename = new FilePathParser(localPath).getFileName();
		String key = p.excludeFirstDir() + "/" + filename;
		bosClient.putObject(bucket, key, file);
		
	}
}
