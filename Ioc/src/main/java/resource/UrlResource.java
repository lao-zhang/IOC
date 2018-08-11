package resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class UrlResource implements Resource{
	private final URL url;
	
	public UrlResource(URL url) {
		this.url = url;
	}
	
	//根据路径返回xml文件的InputStream接口，以便后续读取数据
	public InputStream getInputStream() throws IOException{
		URLConnection connection = url.openConnection();
		connection.connect();
		return connection.getInputStream();
	}
}
