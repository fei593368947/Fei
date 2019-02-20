package com.zs.pms.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogAdvice implements MethodInterceptor {

	/**
	 * 执行拦截方法
	 */
	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		// TODO Auto-generated method stub
		Object o = method.proceed();//执行具体方法
		
		//记录日志
		System.out.println("记录日志:"+method.getMethod().getName()+"------");
		return o;
	}

}
