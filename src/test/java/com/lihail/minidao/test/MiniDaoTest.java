package com.lihail.minidao.test;

import java.sql.SQLException;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lihail.config.DataSourcesConfig;
import com.lihail.config.JpaConfig;
import com.lihail.config.MiniDaoConfig;
import com.lihail.dao.EmployeeDao;
import com.lihail.dao.EmployeeRepository;
import com.lihail.entity.EmployeeEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourcesConfig.class,JpaConfig.class,MiniDaoConfig.class})
@ActiveProfiles("prd")
public class MiniDaoTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeDao employeeDao;
	
	
	
	@Test
	public void testNotNull() throws SQLException {
		
//		System.out.println("===count====："+employeeDao.count());
//		System.out.println("====findEmployees===："+employeeDao.findEmployees("scott"));
//		System.out.println("====findById===："+employeeDao.findById("8496381811DD4A2084439D3DF01512B6"));
//		System.out.println("====findEmployeeMaps===："+employeeDao.findEmployeeMaps("scott","25"));
		
		EmployeeEntity employeeEntity = new EmployeeEntity();
		employeeEntity.setAge("20");
		employeeEntity.setName("scott");
		System.out.println("====selectEmployees===："+employeeDao.selectEmployees(employeeEntity));
		
//		System.out.println(employeeRepository.findById("8496381811DD4A2084439D3DF01512B6"));
	}
}
