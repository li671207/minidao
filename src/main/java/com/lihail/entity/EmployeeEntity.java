package com.lihail.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "employee2")
public class EmployeeEntity {
	
	@Id
	private String id;
	
	private String name;

	@Override
	public String toString() {
		return "EmployeeEntity [id=" + id + ", name=" + name + "]";
	}
	
	

}
