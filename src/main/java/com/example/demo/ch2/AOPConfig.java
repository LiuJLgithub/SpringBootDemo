package com.example.demo.ch2;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用spring boot中的aop
 * 
 * @author Liu
 *
 */
@Configuration
@Aspect
public class AOPConfig {

	@Around("@within(org.springframework.stereotype.Controller)") // @within表示目标带有注解
	public Object simpleAop(final ProceedingJoinPoint pjp) throws Throwable {
		try {
			// 拦截的实体类，就是当前正在执行的controller
//			Object target = pjp.getTarget();
//			System.out.println("target:"+target);
			// 拦截的方法名称。当前正在执行的方法
//			String methodName = pjp.getSignature().getName();
//			System.out.println("methodName:"+methodName);
			// 拦截的方法参数
//			Object[] args = pjp.getArgs();
//			System.out.println("参数长度："+args.length);
//			System.out.println("args:" + Arrays.asList(args));
			// 调用原来的方法
			Object o = pjp.proceed();
			System.out.println("return:" + o);
			return o;
		} catch (Throwable e) {
			throw e;
		}
	}

}
