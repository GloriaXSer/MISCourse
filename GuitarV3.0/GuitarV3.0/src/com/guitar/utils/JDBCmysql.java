package com.guitar.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import com.guitar.idao.IJDBC;

public class JDBCmysql implements IJDBC {
	@Override
	public Connection createConn(){
		String url="jdbc:mysql://localhost:3306/guitar";
		String userName="root";
		String password="Sakura9535";
		Connection conn=null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn=DriverManager.getConnection(url,userName,password);			
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;		
	}
	
}
