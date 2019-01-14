package com.lihail.factory;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

public class MiniDaoDefinitionScanner extends ClassPathBeanDefinitionScanner {

	private static final Logger LOGGER = Logger.getLogger(MiniDaoDefinitionScanner.class);
	
	public MiniDaoDefinitionScanner(BeanDefinitionRegistry registry) {
		super(registry);
	}
	
	@Override
	protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
		Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
		GenericBeanDefinition beanDefinition;
		for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
			beanDefinition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
			beanDefinition.getPropertyValues().add("proxy", getRegistry().getBeanDefinition("miniDaoHandler"));
			beanDefinition.getPropertyValues().add("doInterface", beanDefinition.getBeanClassName());
			LOGGER.info("register minidao name is { " + beanDefinition.getBeanClassName() + " }");
			beanDefinition.setBeanClass(MiniDaoFactory.class);
		}
		return beanDefinitionHolders;
	}
	
	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
	}

}
