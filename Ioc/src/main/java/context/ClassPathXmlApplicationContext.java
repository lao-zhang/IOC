package context;

import java.util.Map;

import definition.BeanDefinition;
import definition.XmlBeanDefinitionReader;
import factory.AbstractBeanFactory;
import factory.AutowireCapableBeanFactory;
import resource.UrlResourceLoader;

public class ClassPathXmlApplicationContext  extends AbstractApplicationContext{
	private String configLocation;
	
	
	//构造方法1
	public ClassPathXmlApplicationContext(String configLocation)throws Exception{
		this(configLocation,new AutowireCapableBeanFactory());
	}
	protected ClassPathXmlApplicationContext(String configLocation,AbstractBeanFactory beanFactory) throws Exception{
		//父类构造方法
		super(beanFactory);
		this.configLocation = configLocation;
		loadBeanDefinition(beanFactory);
	}
	protected void loadBeanDefinition(AbstractBeanFactory beanFactory)throws Exception{
		XmlBeanDefinitionReader xmlBeanDefintionReader = new XmlBeanDefinitionReader(new UrlResourceLoader());
		//这一步加载xml所有数据至读取流程map中
		xmlBeanDefintionReader.loadBeanDefinitions(configLocation);
		//遍历map，将xml读取流程产生的数据传到装配流程的map中
		for(Map.Entry<String, BeanDefinition> entry : xmlBeanDefintionReader.getRegistry().entrySet()) {
			beanFactory.registerBeanDefinition(entry.getKey(),entry.getValue());
		}

	}

}
