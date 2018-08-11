package factory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import definition.BeanDefinition;

public abstract class AbstractBeanFactory implements BeanFactory{
	//也就是获取数据流程中的同款map
	private Map<String,BeanDefinition> beanDefinitionMap =  new ConcurrentHashMap<String,BeanDefinition>();
	private List<String> beanDefinitionNames  = new ArrayList<String>();
	
	public Map<String,BeanDefinition> getMap(){
		return this.beanDefinitionMap;
	}
	//模版方法模式、上层调用下层（好莱坞模式）
	//根据bean名获取bean实例
	public Object getBean(String name) throws Exception{
		//先尝试以键取值
		BeanDefinition beanDefinition  =beanDefinitionMap.get(name);
		//数据为空则抛出bean未定义错误（map里没有这个键）
		if(beanDefinition == null) {
			throw new IllegalArgumentException("No bean named"+name+"is defined");
		}
		//再尝试从值中取bean
		Object bean = beanDefinition.getBean();
		//bean未装载，则调用方法完成装载
		if(bean == null) {
			bean = doCreatBean(beanDefinition);
		}
		return bean;
	}
	
	protected Object doCreatBean(BeanDefinition beanDefinition)throws Exception{
		//获取bean实例
		Object bean =  createBeanInstance(beanDefinition);
		//存入空bean
		beanDefinition.setBean(bean);
		//对bean进行实际装配（导入属性值）
		applyPropertyValues(bean,beanDefinition);
		return bean;
	}
	//根据类型创建类实例(这里创建的是成员属性结尾null的空壳实例)
	protected Object createBeanInstance(BeanDefinition beanDefinition)throws Exception{
		return beanDefinition.getClassType().newInstance();
	}
	//模版方法模式中供用户实现的抽象方法，这里是负责bean属性值的导入
	protected abstract void applyPropertyValues(Object bean,BeanDefinition beanDefinition)throws Exception;
	
	//根据list中的name依次进行bean加载
	public void preInstantiateSingletons() throws Exception{
		Iterator<String> it = this.beanDefinitionNames.iterator();
		while(it.hasNext()) {
			String beanName = it.next();
			getBean(beanName);
		}
	}
	
	//根据传入的类型从键列表中获取匹配的键存入新的列表（需要从map取值进行实际的类型比对）
	public List<Object> getBeanForType(Class<?> classType)throws Exception{
		List<Object> list =  new ArrayList<Object>();
		for(String beanDefinitionName : beanDefinitionNames) {
			if(classType.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getClassType())) {
				list.add(getBean(beanDefinitionName));
			}
		}
		return list;
	}
	//给map添值，给键列表添值
	public void registerBeanDefinition(String name,BeanDefinition beanDefinition) {
		beanDefinitionMap.put(name, beanDefinition);
		beanDefinitionNames.add(name);
	}
}
