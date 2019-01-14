package com.lihail.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "employee")
public class EmployeeEntity extends BaseEntity{
	
	@Id
	@GenericGenerator(name="idgen", strategy="uuid")
	@GeneratedValue(generator="idgen")
	private String id;
	
	private String empno;
	private String name;
	private String age;
	private String birthday;
	private String salary;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [id=" + id + ", empno=" + empno + ", name=" + name + ", age=" + age + ", birthday="
				+ birthday + ", salary=" + salary + "]";
	}

}
