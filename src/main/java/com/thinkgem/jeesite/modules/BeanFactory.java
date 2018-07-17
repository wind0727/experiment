package com.thinkgem.jeesite.modules;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanFactory implements ApplicationContextAware 
{
	private static ApplicationContext theContext;
	
	public void setApplicationContext(ApplicationContext context) throws BeansException 
	{
		BeanFactory.theContext = context;
	}
	
	public static Object getBean(String beanName){
		return theContext.getBean(beanName);
	}
}