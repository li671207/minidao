package com.lihail.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lihail.annotation.DynamicSql;
import com.lihail.annotation.Modify;
import com.lihail.annotation.MyParam;
import com.lihail.annotation.Paging;
import com.lihail.dict.ModifyType;
import com.lihail.entity.EmployeeEntity;

@Repository
public interface EmployeeDao {
	
	Integer count();
	
	@DynamicSql("select * from employee where id=:id")
	EmployeeEntity findById(@MyParam("id") String id);
	
	@DynamicSql("select * from employee where name=:name")
	List<EmployeeEntity> findEmployees(@MyParam("name") String name);
	
	@DynamicSql("select * from employee where name = ? or age = ?")
	List<Map<String, Object>> findEmployeeMaps(String name, String age);
	
	List<EmployeeEntity> selectEmployees(@MyParam("employee") EmployeeEntity employeeEntity);
	
	@Paging()
	@DynamicSql("select * from employee order by id desc limit {0},{1}")
	List<EmployeeEntity> selectEmployeesByPage(@MyParam("employee") EmployeeEntity employeeEntity, @MyParam("pageNo") String pageNo, @MyParam("pageSize") String pageSize);
	
	@Paging()
	@DynamicSql("select * from employee order by id desc limit {0},{1}")
	List<EmployeeEntity> selectEmployeesByPage(@MyParam("pageNo") String pageNo, @MyParam("pageSize") String pageSize);
	
	@Modify()
	int update(EmployeeEntity employeeEntity);
	
	@Modify(ModifyType.INSERT)
	int save(EmployeeEntity employeeEntity);
	
	@Modify(ModifyType.DELETE)
	int delete(@MyParam("id") String id);
}
