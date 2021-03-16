package stu.week05.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import stu.week05.spring.service.TestService;

public class SpringDemo {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TestService service = (TestService)context.getBean("testService");
        service.addService();
    }
}
