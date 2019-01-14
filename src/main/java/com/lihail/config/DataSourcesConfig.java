package com.lihail.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class DataSourcesConfig {

	@Autowired
	private Environment environment;
	
	@Bean(destroyMethod = "shutdown")
	@Profile("dev")
	public DataSource embeddedDatabase() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.build();
	}
	
	@Bean
	@Profile("prd")
	public DataSource druidDatabase() {
		DruidDataSource druid = new DruidDataSource();
		druid.setUrl(environment.getProperty("jdbc.url"));
		druid.setUsername(environment.getProperty("jdbc.username"));
		druid.setPassword(environment.getProperty("jdbc.password"));
		
		return druid;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(druidDatabase());
	}
	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(druidDatabase());
	}
}
