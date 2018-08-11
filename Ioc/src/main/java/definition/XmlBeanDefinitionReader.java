package definition;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bean.BeanReference;
import bean.PropertyValue;
import resource.ResourceLoader;


public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{
	public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
		//父类构造方法
		super(resourceLoader);
	}
	//加载bean数据
	@Override
	public void loadBeanDefinitions(String location) throws Exception{
		//获取inputStream接口
		InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
		//数据加载
		doLoadBeanDefinitions(inputStream);
	}
	protected void doLoadBeanDefinitions(InputStream inputStream)throws Exception{
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
		
		//读取文件
		Document doc  = documentBuilder.parse(inputStream);
		//处理文件，导出数据
		registerBeanDefinitions(doc);
		inputStream.close();
	}
	//处理<beans>标签
	//xml以<beans>为根目录，将根目录之间的内容装入root
	protected void registerBeanDefinitions(Document doc) {
		Element root = doc.getDocumentElement();
		parseBeanDefinition(root);
	}
	//处理<bean>标签
	protected void parseBeanDefinition(Element root) {
		//根目录之间的内容以列表形式遍历、处理（若干<bean>）
		NodeList nodeList = root.getChildNodes();
		for(int i=0;i<nodeList.getLength();i++) {
			Node node = nodeList.item(i);
			if(node instanceof Element) {
				Element ele = (Element)node;
				//每一个<bean>存放的数据进行处理
				processBeanDefinition(ele);
			}
		}
	}
	//生成并注册BeanDefinition（存放类信息）
	protected void processBeanDefinition(Element ele) {
		String name = ele.getAttribute("id");
		String className = ele.getAttribute("class");
		
		BeanDefinition beanDefinition = new BeanDefinition();
		//装填属性值
		processProperties(beanDefinition,ele);
		
		//装填className
		beanDefinition.setClassName(className);
		//存入Map（至此整个从xml加载数据流程结束）
		getRegistry().put(name,beanDefinition);
	}
	//解析<property>标签（存放属性值） （一个<bean> 下可能有若干<property>）
	protected void processProperties(BeanDefinition beanDefinition,Element ele ) {
		NodeList propertyValues = ele.getChildNodes();
		
		for(int i=0;i<propertyValues.getLength();i++) {
			Node node = propertyValues.item(i);
			
			if(node instanceof Element) {
				Element element = (Element) node;
				String name = element.getAttribute("name");
				String value= element.getAttribute("value");
				
				//如果是非空value（属性值）则以（name-value）形式存入PropertyValue
				if(value!=null&&value.length()>0) {
					//装填属性值
					beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,value));
				}
				else {
					String ref = element.getAttribute("ref");
					//如果是非空ref（class实例），则以（name-BeanReference(ref)）形式存入PropertyValue
					//否则抛出错误，提示<property>必须非空
					if(ref==null||ref.length()==0) {
						throw new IllegalArgumentException("Configuration problem:<property>element for property ' "+name+" 'must specify a ref or value ");
					}
					beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,new BeanReference(ref)));
				}
			}
		}
	}
	
}
