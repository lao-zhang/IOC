package context;

import test.HelloWorldService;

public class Main {
	public static void main(String[]args) throws Exception {
		//指定了bean数据的来源，接着完成加载数据，并且把数据传给装配流程
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
		//以类名名称取bean实例
		//调用装配流程的genBean方法，现场进行装配
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();
	}
}
