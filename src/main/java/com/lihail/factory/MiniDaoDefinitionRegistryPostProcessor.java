package com.lihail.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

import com.lihail.dict.DbType;
import com.lihail.minidao.MiniDaoHandler;

public class MiniDaoDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
	
	private String basePackage;
	private DbType dbType;
	
	public void setDbType(DbType dbType) {
		this.dbType = dbType;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
	}

	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(MiniDaoHandler.class);
		beanDefinition.getPropertyValues().add("dbType", dbType);
		registry.registerBeanDefinition("miniDaoHandler", beanDefinition);
		
		MiniDaoDefinitionScanner definitionScanner = new MiniDaoDefinitionScanner(registry);
		definitionScanner.scan(StringUtils.tokenizeToStringArray(this.basePackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
	}

}
