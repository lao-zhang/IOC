package definition;

import java.util.HashMap;

import resource.ResourceLoader;
/*
 * BeanDefinition : bean实例、bean类名、bean 类型、bean内属性值（K-V）
 * */
public class AbstractBeanDefinitionReader implements BeanDefinitionReader{
	//存放原材料（从xml文件加载的数据，还未组装成bean实例）
	private HashMap<String,BeanDefinition> registry = new HashMap<String,BeanDefinition>();
	
	private ResourceLoader resourceLoader;
	
	//构造方法（）
	protected AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
		this.resourceLoader=resourceLoader;
	}
	
	public HashMap<String,BeanDefinition> getRegistry(){
		return registry;
	}
	//返回xml的InputStream接口
	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}
	
	//加载流程（重头戏）
	@Override
	public void loadBeanDefinitions(String location) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(location);
	}
	
}
