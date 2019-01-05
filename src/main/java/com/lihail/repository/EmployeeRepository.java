package com.lihail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lihail.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String>{
	
}
