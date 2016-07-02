package com.system.action;


import org.junit.Test;

import com.system.dao.StudentDao;
import com.system.dao.impl.StudentDaoImpl;
import com.system.model.Student;

public class LoginActionTest {

	@Test
	public void test() throws Exception{
		Student user = new Student();
		StudentDao studentDao = new StudentDaoImpl();
		user = studentDao.getOneStudent("01140001");
		System.out.println(user.getName());
	}

}
