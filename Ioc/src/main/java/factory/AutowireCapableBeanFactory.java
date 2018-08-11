package factory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import bean.BeanReference;
import bean.PropertyValue;
import definition.BeanDefinition;
import ext.BeanFactoryAware;

//此子类具体实现父类中抽象方法（如何给bean装载属性值）
public class AutowireCapableBeanFactory extends AbstractBeanFactory{
	@Override
	protected void applyPropertyValues(Object bean,BeanDefinition beanDefinition) throws Exception{
		if(bean instanceof BeanFactoryAware) {
			((BeanFactoryAware)bean).setBeanFactory(this);
		}
		//遍历属性值列表
		for(PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValueList()) {
			Object value = propertyValue.getValue();
			if(value instanceof BeanReference) {
				BeanReference beanReference = (BeanReference)value;
				value=getBean(beanReference.getName());
			}
			try {
				//调用bean的set方法（此调用是按照set方法的默认命名规则来进行的   例：setName( )，“set”小写，属性名首字母大写）
				Method method = bean.getClass().getDeclaredMethod(
						"set"+propertyValue.getName().substring(0, 1).toUpperCase()
						+propertyValue.getName().substring(1), value.getClass());
				//确保能调用private方法
				method.setAccessible(true);
				//方法调用
				method.invoke(bean, value);
			}catch(NoSuchMethodException e) {
				/*
				 * 如果找不到bean某个属性的set方法，（也许是没有set方法，也许是没有按照默认规则命名）
				 * 则直接利用反射来进行属性赋值
				 */
				Field field = bean.getClass().getDeclaredField(propertyValue.getName());
				//保证能为private属性赋值
				field.setAccessible(true);
				//赋值
				field.set(bean, value);
			}
		}
	}
}
