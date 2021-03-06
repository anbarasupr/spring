package com.spring.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.Coach;

public class PrototypeAnnotationDemoApp {

	public static void main(String[] args) {

		// read spring config file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// get the bean from spring container
		Coach theCoach = context.getBean("fieldCoach", Coach.class);
		Coach theCoach2 = context.getBean("fieldCoach", Coach.class);
		System.out.println("theCoach:"+theCoach+", theCoach2:"+theCoach2+", isSingleton:"+(theCoach==theCoach2));
		
		
		// close the context
		context.close();
		
	}

}


