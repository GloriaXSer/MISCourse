package com.system.model;

public abstract class Person {
	private String id;
	private String name;
	
	public Person(String name, String id){
		this.setId(id);
		this.setName(name);
	}
	public Person(){
		
	}
	public Person(String id){
		this.id = id;
	}
	//属性初始化
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
}
