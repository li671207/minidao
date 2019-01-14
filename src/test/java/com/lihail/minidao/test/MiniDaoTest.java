package com.lihail.minidao.test;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
		
//		System.out.println(employeeRepository.findById("8496381811DD4A2084439D3DF01512B6"));
		
//		System.out.println("====findEmployees===："+employeeDao.findEmployees("scott"));
		
//		System.out.println("====findById===："+employeeDao.findById("8496381811DD4A2084439D3DF01512B6"));
		
		System.out.println("====findEmployeeMaps===："+employeeDao.findEmployeeMaps("scott"));
		
//		KeyHolder keyHolder = new GeneratedKeyHolder();
		//INSERT INTO employee ( id ,empno ,NAME ,AGE ,BIRTHDAY ,SALARY ,create_by ,create_date ,update_by ,update_date ) values ( :employee.id ,:employee.empno ,:employee.name ,:employee.age ,:employee.birthday ,:employee.salary ,:employee.createBy ,:employee.createDate ,:employee.updateBy ,:employee.updateDate )
		//{employee.id=null, employee.empno=200, employee.age=20, employee.createDate=Sat Jan 05 16:13:57 GMT+08:00 2019, employee.name=scott, employee.birthday=Sat Jan 05 16:13:57 GMT+08:00 2019, employee.salary=88888, employee.createBy=scott, employee.updateBy=null, employee.updateDate=null}
//		jdbcTemplate.update("insert into employee2 (id,name) values ('"+ UUID.randomUUID().toString() +"','test1')");
//		employeeDao.count();
//		System.out.println(jdbcTemplate.queryForList("select * from employee2"));
//		System.out.println(employeeRepository.findAll());
//		jdbcTemplate.update("insert into employee2 (name) values ('ccc')");
//		HashMap<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("name", "generateId");
//		MapSqlParameterSource paramSource = new MapSqlParameterSource(paramMap); 
//		namedParameterJdbcTemplate.update("insert into employee2 (name) values (:name)", paramSource, keyHolder, new String[] {"id"});
//		System.out.println(keyHolder.getKey());
//		EmployeeEntity entity = new EmployeeEntity();
//		entity.setName("测试");
//		employeeRepository.save(entity);
	}
}
