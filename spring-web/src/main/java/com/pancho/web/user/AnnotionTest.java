package com.pancho.web.user;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotionTest {

	@Test
    public void testAOP(){
        //启动Spring容器        
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:config/spring/application-context.xml"});
        //获取controller组件
        UserController userController = (UserController) ctx.getBean("userController");
        userController.testAOP("张三", "123456");
    }
}
