package com.lihail.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.lihail.aspect.MiniDaoAspect;
import com.lihail.factory.MiniDaoDefinitionRegistryPostProcessor;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.lihail")
public class MiniDaoConfig {
	
	@Bean
	public MiniDaoAspect miniDaoAspect() {
		return new MiniDaoAspect();
	}
	
	@Bean
	public MiniDaoDefinitionRegistryPostProcessor miniDaoDefinitionRegistryPostProcessor(Environment environment) {
		MiniDaoDefinitionRegistryPostProcessor processor = new MiniDaoDefinitionRegistryPostProcessor();
		processor.setBasePackage("com.lihail");
		return processor;
	}

}
