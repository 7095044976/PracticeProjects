package com.example.Practice.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private String name;
private String gmail;
private double salary;

public User() {
	super();
	// TODO Auto-generated constructor stub
}
public User(long id, String name, String gmail, double salary) {
	super();
	this.id = id;
	this.name = name;
	this.gmail = gmail;
	this.salary = salary;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getGmail() {
	return gmail;
}
public void setGmail(String gmail) {
	this.gmail = gmail;
}
public double getSalary() {
	return salary;
}
public void setSalary(double salary) {
	this.salary = salary;
}
@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", gmail=" + gmail + ", salary=" + salary + "]";
}


}