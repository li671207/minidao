package com.lihail.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MiniDaoAspect {
	
	@Pointcut("execution(** com.lihail.repository.EmployeeDao.count(..))")
	public void count() {
		
	}
	
	@Before("count()")
	public void initCount() {
		System.out.println("initCount.....");
	}

}
