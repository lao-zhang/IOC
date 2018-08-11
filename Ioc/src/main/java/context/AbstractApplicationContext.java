package context;

import factory.AbstractBeanFactory;

public abstract class AbstractApplicationContext implements ApplicationContext{
	protected AbstractBeanFactory beanFactory;
	
	public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
	
	//调用此方法，才能展开整个Ioc流程
	public void refresh() throws Exception{
		loadBeanDefinition(beanFactory);
		onRefresh();
	}
	
	//又一个模版方法模式
	protected abstract void loadBeanDefinition(AbstractBeanFactory beanFactory)throws Exception;
	
	//遍历键列表进行map内bean的装载
	protected void onRefresh() throws Exception{
		beanFactory.preInstantiateSingletons();
	}
	//根据name获取bean实例（？）
	public Object getBean (String name)throws Exception {
		return beanFactory.getBean(name);
	}
	
}
