
测试用类，对应实际项目中的事务类。

采用接口-接口实现类结构，是遵循了spring框架的准则：

设计了类调用的结构（HelloworldServiceImpl需要通过OutputServiceImpl来输出内容），是为了使两种赋值都涉及到：
	XML中<bean>标签下的数据值对应有两种：1.name-value （单纯的成员属性赋值），2.name-ref （给成员类指定一个实例的引用）