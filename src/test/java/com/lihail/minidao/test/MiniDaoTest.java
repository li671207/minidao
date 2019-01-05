package com.lihail.minidao.test;

import java.sql.SQLException;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lihail.config.DataSourcesConfig;
import com.lihail.config.JpaConfig;
import com.lihail.config.MiniDaoConfig;
import com.lihail.minidao.MiniDaoHandler;
import com.lihail.repository.EmployeeDao;
import com.lihail.repository.EmployeeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourcesConfig.class,JpaConfig.class,MiniDaoConfig.class})
@ActiveProfiles("prd")
public class MiniDaoTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeDao employeeDao;

	@Test
	public void testNotNull() throws SQLException {
//		jdbcTemplate.execute("insert into employee2 (id,name) values ('"+ UUID.randomUUID().toString() +"','test')");
//		employeeDao.count();
//		System.out.println(jdbcTemplate.queryForList("select * from employee2"));
		System.out.println(employeeRepository.findAll());
	}
}
