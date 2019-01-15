package com.lihail.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lihail.annotation.DynamicSql;
import com.lihail.annotation.MyParam;
import com.lihail.annotation.Modify;
import com.lihail.entity.EmployeeEntity;

@Repository
public interface EmployeeDao {
	
	Integer count();
	
	@DynamicSql("select * from employee where id=:id")
	EmployeeEntity findById(@MyParam("id") String id);
	
	@DynamicSql("select * from employee where name=:name")
	List<EmployeeEntity> findEmployees(@MyParam("name") String name);
	
	@DynamicSql("select * from employee where name= ?")
	List<Map<String, Object>> findEmployeeMaps(@MyParam("name") String name);
	
	List<EmployeeEntity> findAll(@MyParam("employee") EmployeeEntity employeeEntity);
	
	@Modify
	int save(EmployeeEntity employeeEntity);
	@Modify
	int delete(@MyParam("id") String id);
}
