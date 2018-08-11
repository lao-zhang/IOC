package resource;

import java.net.URL;

public class UrlResourceLoader implements ResourceLoader{
	public Resource getResource(String location) {
		//获取此类的路径（这也正是xml的路径，前提是我们把xml放在同一个包下）
		//再加上传入的文件名（location）,我们就得到了xml的完整路径
		URL url = this.getClass().getClassLoader().getResource(location);
		System.out.println("url is: "+url);
		return new UrlResource(url);
	}

}
