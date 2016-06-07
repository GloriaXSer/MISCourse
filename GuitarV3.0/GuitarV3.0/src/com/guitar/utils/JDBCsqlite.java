package com.guitar.utils;

import java.sql.*;

import com.guitar.idao.IJDBC;

public class JDBCsqlite implements IJDBC {
	@Override
	public Connection createConn(){	
		Connection connection=null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection= DriverManager.getConnection("jdbc:sqlite:C:/sqlite/guitarV3.db");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}
