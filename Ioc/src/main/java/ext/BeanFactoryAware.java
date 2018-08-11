package ext;

import factory.BeanFactory;

public interface BeanFactoryAware {
	void setBeanFactory(BeanFactory beanFactory)throws Exception;
}
