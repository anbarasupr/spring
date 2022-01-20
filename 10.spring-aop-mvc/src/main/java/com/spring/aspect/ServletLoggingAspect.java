package com.spring.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class ServletLoggingAspect {

	// setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());

	// setup pointcut declarations
	
	@Pointcut("execution(* com.servlet.WelcomeServlet.*(..))")
	//@Pointcut("execution(* javax.servlet.http.HttpServlet.service(..))")
	private void forTestPackage() {
	}
	

	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {

		// display method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("=====>>ServletLoggingAspect in @Before: calling method: " + theMethod);

		// display the arguments to the method

		// get the arguments
		Object[] args = theJoinPoint.getArgs();

		// loop thru and display args
		for (Object tempArg : args) {
			myLogger.info("=====>>ServletLoggingAspect argument: " + tempArg);
		}

	}

	
}
