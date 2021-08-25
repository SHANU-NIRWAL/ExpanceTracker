package com.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {

	@Before("execution (* com.controller.AppController.signup(..))")
	public void preProcess() {
		System.out.println("preprocess");

	}

	@After("execution (* com.controller.AppController.logout(..))")
	public void postProcess() {
		System.out.println("postprocess");
	}
}
