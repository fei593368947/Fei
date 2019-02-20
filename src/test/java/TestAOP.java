import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.advice.LogAdvice;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService2;
import com.zs.pms.serviceImpl.UserServiceImpl2;
import com.zs.pms.vo.QueryUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationcontext.xml")
public class TestAOP {

	
	@Test
	public void testlog() {
		//创建业务对象
		UserService2 us = new UserServiceImpl2();
		
		//创建日志通知  --记录日志
		LogAdvice la = new LogAdvice();
		//创建切点
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		//拦截以add开头的方法
		pointcut.addMethodName("add*");
		//拦截以update开头的方法
		pointcut.addMethodName("update*");
		//创建切面日志 = 切点+通知
		Advisor advisor = new DefaultPointcutAdvisor(pointcut, la);
		
		//由代理把把切面织入目标
		//代理  工厂
		ProxyFactory factory = new ProxyFactory();
		//代理有了记录日志的服务  织入
		factory.addAdvisor(advisor);
		//代理目标是UserService2  --业务方法
		factory.setTarget(us);
		
		//从代理获得业务对象
		UserService2 us2 = (UserService2) factory.getProxy();
		TUser user = new TUser();
		us2.addUser(user);//新增方法
		us2.updateUser(user);//修改方法
	}
	
}
