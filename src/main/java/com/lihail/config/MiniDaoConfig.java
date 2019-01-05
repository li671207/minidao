package com.lihail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.lihail.aspect.MiniDaoAspect;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.lihail")
public class MiniDaoConfig {
	
	@Bean
	public MiniDaoAspect miniDaoAspect() {
		return new MiniDaoAspect();
	}

}
