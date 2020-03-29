package main;

import com.spring.DemoApplication;
import com.spring.config.ProjectConfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.spring.services.CustomerService;

public class Main {

    public static void main(String[] args) {
        try (AbstractApplicationContext c = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
            CustomerService p = c.getBean(CustomerService.class);
            p.addCustomer("qwerty");
        }
    	
//    	try (AnnotationConfigApplicationContext c = new AnnotationConfigApplicationContext(DemoApplication.class)) {
//            CustomerService p = c.getBean(CustomerService.class);
//            p.addCustomer("test");
//        }
    }
}
