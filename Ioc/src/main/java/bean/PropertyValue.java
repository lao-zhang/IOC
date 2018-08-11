package bean;

//name-value
//对应xml中存放的属性值数据
public class PropertyValue {
	private final String name;
	private final Object value;
	
	

	public PropertyValue(String name,Object value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	public Object getValue() {
		return value;
	}

}
