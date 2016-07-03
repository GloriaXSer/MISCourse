package com.system.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCmysql{
	
	public static Connection createConn(){
		String url="jdbc:mysql://localhost:3306/selection";
		String userName="root";
		String password="123456";
		Connection connection=null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(url,userName,password);			
		}catch(Exception e){
			e.printStackTrace();
		}
		return connection;		
	}	
}
