package bean;

import java.util.ArrayList;
import java.util.List;
//属性值数据列表
//xml中一个<bean>标签下有时有多个属性值数据
public class PropertyValues {
	private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();
	
	public PropertyValues() {	
	}
	
	public List<PropertyValue> getPropertyValueList() {
		return this.propertyValueList;
	}
	

	public void addPropertyValue(PropertyValue propertyValue) {
		this.propertyValueList.add(propertyValue);
	}
}
