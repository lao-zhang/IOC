package definition;

import org.junit.Assert;
import org.junit.Test;
import resource.UrlResourceLoader;

import java.util.Map;

/**
 * Created by clxy on 2017/4/16.
 */
public class XmlBeanDefinitionTest {
    @Test
    public void Test() throws Exception{
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new UrlResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");
        Map<String,BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
        Assert.assertTrue(registry.size() > 0);

    }
}
