
Resource 接口是返回目标文件的InputStream接口以供后续读取数据使用。

ResourceLoader 接口则是在Resource 之前做一些准备工作。

具体实现：
	这里使用了URL类族进行XML文件的处理
	
	UrlResourceLoader，ResourceLoader的实现类，通过this.getClass().getClassLoader().getResource(String Filename)
	方法获取了存放bean数据的XML文件的完整路径，当然前提是我们需要保证XML文件在运行时资源载入类运行的那个包下面，
	不然上面的方法是取不到文件的路径的（因为这里是this调用）。获取的路径信息存入URL类，接着直接将其做为参数构造一
	个UrlResource实例，并作为返回值。
	
	UrlResource，Resource的实现类，内置URL成员（存放文件完整路径），内部通过URLConnection获取URL的
	InputStream接口，并作为返回值。
	
	
	