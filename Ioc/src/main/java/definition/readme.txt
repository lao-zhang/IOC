

使用Document类族，通过InputStream进行xml文件数据的导出。

整个过程层层递进，按照Dom结构进行，也让人初步感觉到xml存储数据的优良性

无public构造方法，类单例构造
DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

无构造方法，通过DocumentBuilderFactory 实例进行实例获取
DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();

通过xml文件的inputStream接口构造，这里是对xml标签进行处理的真正入口
Document doc = documentBuilder.parse(inputStream);

获取xml根元素内容(<beans>)
Element root = doc.getDocumentElement();

获取根元素下的所有子节点（<bean>）
NodeList nodeList = root.getChildNodes();

遍历所有子节点
for(int i=0;i<nodeList.getLength();I++){
	取单个节点
	Node node = nodeList.item(i);
	类型检查
	if(node instanceof Element){
		类型转换，这里就依次取到所有子节点了
		Element ele = (Element)node;	
	}
}

子节点数据获取
Element ele
取id和classname：<bean id="xxx" class="xxx">
String name = ele.getAttribute("id");
String className = ele.getAttribute("value");

解析<property>
NodeList propertyValues = ele.getChildNodes();
for(int i=0;i=propertyValues.getLength();i++){
	Node node = propertyValues.item(i);
	if(node instanceof Element){
		Element element = (Element)node;
		取name和value：<property name="xxx" value="xxx">
		String name = element.getAttribute("name");
		String value = element.getAttribute("value");
		value存在
		if(value!=null&&value.length()>)){
		
		}
		value不存在，则意味着需要取ref：<property name="xxx" ref="xxx">
		else{
			String ref = element.getAttribute("ref");
			如果ref也为空，则抛错
			if(ref==null||ref.length()==0)
				throw new Exception();		
		}
	}
}

