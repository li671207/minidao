package com.lihail.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lihail.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String>{
	
	@Query(nativeQuery=true, value="select * from employee where id=:id")
	EmployeeEntity findById(@Param("id") String id);
	
}
