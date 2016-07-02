package com.system.model.test;

public abstract class PersonTest {
	private String id;
	private String name;
	
	public PersonTest(String name, String id){
		this.setId(id);
		this.setName(name);
	}
	public PersonTest(){
		
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
	//公共方法
	public abstract String toString();
	
	public void display() {
		System.out.println("Person Information");
		System.out.println("\tSoc. Security No.:" + this.getId());
		System.out.println("\tName:" + this.getName());
	}
	

}
