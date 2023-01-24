package aspect.aspects;

import aspect.beans.MyImplementation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
public class MyAspect {

	//declare itroduction
	@DeclareParents(value="aspect.beans.MyImplementation", defaultImpl=MyIntroductionImpl.class)
	public static MyIntroduction introduction;


	@Before("execution(public void doSomething())")
	public void myPointcutExecution() {
		System.out.println("pointcut execution");
	}

	//@Pointcut used only to define expression, no effect if not used in Advice
	@Pointcut("within(aspect.beans.*)")
	public void definePointCut() {}

	@After("definePointCut()")
	public void myPointcutWithin() {
		System.out.println("pointcut within");
	}

	//around, implementation of MyInterface
	@Around("target(aspect.beans.MyInterface)")
	public Object myAroundAdvice(ProceedingJoinPoint pjp) throws Throwable {

		//pre joint point
		System.out.println("pre around");

		//call joint point logic
		Object result = pjp.proceed();

		//post joint point
		System.out.println("post around");

		return result;
	}

	//method with arguments and access to the arguments
	@Before("execution(public String doSomethingWithPar(..)) && args(par)")
	public void beforeWithParameter(String par) {
		System.out.println(par);
	}

	//Using introduction
	@After("execution(public String doSomethingWithPar(..)) && this(introduction)")
	public void beforeWithParameter(MyIntroduction introduction) {
		introduction.introductionLogic();
	}
}
