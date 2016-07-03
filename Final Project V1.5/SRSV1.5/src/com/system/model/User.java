package com.system.model;

import com.system.dao.StudentDao;
import com.system.dao.impl.StudentDaoImpl;

public class User {
	private String id;
	private String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String isLogined(String id, String password) throws Exception{
		StudentDao studentDao = new StudentDaoImpl();
		String logined = studentDao.getLogined(id, password);
		
		if("admin".equals(id) && "iamadmin".equals(password)){
			return "/adminindex.jsp";
		}
		else if("success".equals(logined)){
			return "/studentindex.jsp";
		}
		else{
			return "/index.jsp";
		}		
	}
}
