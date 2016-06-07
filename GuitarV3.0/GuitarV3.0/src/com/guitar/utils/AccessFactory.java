package com.guitar.utils;
import java.sql.Connection;
import com.guitar.idao.GuitarIDAO;
import com.guitar.idao.IJDBC;
import com.guitar.mysqldao.GuitarMysqlDAO;
import com.guitar.sqlitedao.GuitarSqliteDAO;

public class AccessFactory {
//	public String dataAccess = "SQLite";
	public String dataAccess = "MySQL";
	public AccessFactory(){}
	
	public GuitarIDAO createDAO(){
		
		GuitarIDAO guitar = null;
		
		switch(dataAccess){
		
			case "SQLite":				
				guitar = new GuitarSqliteDAO();break;
				
			default:
				guitar = new GuitarMysqlDAO();		
		}
		return guitar;
	}
/**
 * 可以把接口也放在工厂中，然后GuitarSqliteDAO中
 * AccessFactory factory = new AccessFactory();
 * Connection conn = factory.createConnection();
 * 这样也可以一并切换
 */
//	public Connection createConnection(){
//		IJDBC jdbc = null;
//		
//		switch(dataAccess){
//		
//			case "SQLite":				
//				jdbc = new JDBCsqlite();break;
//			
//			default:
//				jdbc = new JDBCmysql();		
//		}
//		return jdbc.createConn();	
//	}
}
