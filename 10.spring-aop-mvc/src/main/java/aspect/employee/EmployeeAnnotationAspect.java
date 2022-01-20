package aspect.employee;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class EmployeeAnnotationAspect {

	@Before("@annotation(aspect.model.Loggable)")
	public void myAdvice(){
		System.out.println("Executing myAdvice!!");
	}
}
