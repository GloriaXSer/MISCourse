package com.system.utils;

import java.sql.*;


public class JDBCsqlite{
	
	public static Connection createConn(){	
		Connection connection=null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection= DriverManager.getConnection("jdbc:sqlite:C:/sqlite/selection.db");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}
