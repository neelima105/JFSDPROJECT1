package klu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="faculty")
public class Faculty {
	@Id
	@Column(name="id")
Long id;
	@Column(name="name")
String name;
	@Column(name="designation")
String designation;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
@Override
public String toString() {
	return "Faculty [id=" + id + ", name=" + name + ", designation=" + designation + "]";
}
}
