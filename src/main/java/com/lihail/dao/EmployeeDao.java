package com.lihail.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lihail.annotation.DynamicSql;
import com.lihail.annotation.MyParam;
import com.lihail.entity.EmployeeEntity;

@Repository
public interface EmployeeDao {
	
	@DynamicSql
	Integer count();
	
	@DynamicSql("select * from employee where id=:id")
	EmployeeEntity findById(@MyParam("id") String id);
	
	@DynamicSql("select * from employee where name=:name")
	List<EmployeeEntity> findEmployees(@MyParam("name") String name);
	
	@DynamicSql("select * from employee where name=:name")
	List<Map<String, Object>> findEmployeeMaps(@MyParam("name") String name);
	
	@DynamicSql
	List<EmployeeEntity> findAll(@MyParam("name") String name);
	
	@DynamicSql
	boolean save(EmployeeEntity employeeEntity);
	
	@DynamicSql
	boolean delete(@MyParam("id") String id);
}
