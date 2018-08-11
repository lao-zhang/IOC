package definition;

import bean.PropertyValues;

public class BeanDefinition {
	//bean
	private Object bean;
	//bean的类名
	private String ClassName;
	//bean的class类型
	private Class ClassType;
	//bean的若干属性和它们的值
	private PropertyValues propertyValues  = new PropertyValues();
	
	public Object getBean() {
		return bean;
	}
	public void setBean(Object bean) {
		this.bean = bean;
	}
	public String getClassName() {
		return ClassName;
	}
	public void setClassName(String className) {
		ClassName = className;
		try {
			ClassType = Class.forName(className);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Class getClassType() {
		return ClassType;
	}
	public void setClassType(Class classType) {
		ClassType = classType;
	}
	
	
	public PropertyValues getPropertyValues() {
		return propertyValues;
	}
	
	public void setPropertyValues(PropertyValues propertyValues) {
		this.propertyValues = propertyValues;
	}
	
	

}
