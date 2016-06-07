package com.guitar.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.guitar.idao.GuitarIDAO;
import com.guitar.idao.IJDBC;
import com.guitar.model.*;
import com.guitar.utils.JDBCmysql;

public class GuitarMysqlDAO implements GuitarIDAO {

	private List guitars;
	private IJDBC jdbc = new JDBCmysql();
	private Connection connection = jdbc.createConn();	
	private PreparedStatement ps = null;

	public GuitarMysqlDAO() {
		guitars = new LinkedList();
	}
	
	@Override
	public void addGuitar(String serialNumber, String backWood, String topWood, double price, String builder, String model, String type, int numStrings) throws Exception{		
		ps = connection
			.prepareStatement("insert into Guitar(serialNumber, backWood, topWood, price, builder, model, type, numStrings) values(?,?,?,?,?,?,?,?)");
		ps.setString(1, serialNumber);
		ps.setString(2, backWood);
		ps.setString(3, topWood);
		ps.setDouble(4, price);
		ps.setString(5, builder);
		ps.setString(6, model);
		ps.setString(7, type);
		ps.setInt(8, numStrings);
		
		ps.executeUpdate();
		ps.close();
	}
	
	@Override
	public void deleteGuitar(String serialNumber) throws Exception {			
		ps = connection
			.prepareStatement("delete from Guitar where serialNumber=?");
		ps.setString(1, serialNumber);
		
		ps.executeUpdate();
		ps.close();
	}
	
	@Override
	public void guitarAdder(String serialNumber, double price,GuitarSpec spec) {
		Guitar guitar = new Guitar(serialNumber, price, spec);
		guitars.add(guitar);
	}
	
	@Override
	public List<Guitar> findAll()throws Exception{		
		List<Guitar> guitars = new ArrayList<Guitar>();
		
		ps = connection
			.prepareStatement("select * from Guitar");
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Guitar guitar = new Guitar(rs.getString(1),rs.getDouble(4), 
					new GuitarSpec(
							Builder.valueOf(rs.getString(5)),
							rs.getString(6),
							Type.valueOf(rs.getString(7)),
							Integer.parseInt(rs.getString(8)),
							Wood.valueOf(rs.getString(2)), 
							Wood.valueOf(rs.getString(3))));
			guitars.add(guitar);
		}
		if(rs != null){
			rs.close();
		}
		ps.close();
		connection.close();	
		return guitars;
	}
	
	@Override
	public Guitar getGuitar(String serialNumber) {
		for (Iterator i = guitars.iterator(); i.hasNext(); ) {
			Guitar guitar = (Guitar)i.next();
			if (guitar.getSerialNumber().equals(serialNumber)) {
				return guitar;
			}
		}
		return null;
	}
	
	@Override
	public List<Guitar> search(GuitarSpec searchSpec) {
		List<Guitar> matchingGuitars = new LinkedList();
		for (Iterator i = guitars.iterator(); i.hasNext(); ) {
			Guitar guitar = (Guitar)i.next();
			if (guitar.getSpec().matches(searchSpec)){
				matchingGuitars.add(guitar);
			}				
		}
		return matchingGuitars;
	}
	
	
	public void initializeGuitar(GuitarIDAO guitardao) throws NumberFormatException, Exception{		
		try {
				ps = connection.prepareStatement("select * from Guitar");
	
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){			
					guitardao.guitarAdder(rs.getString(1),rs.getDouble(4), 
										new GuitarSpec(Builder.valueOf(rs.getString(5)),
														rs.getString(6),
														Type.valueOf(rs.getString(7)),
														Integer.parseInt(rs.getString(8)),
														Wood.valueOf(rs.getString(2)), 
														Wood.valueOf(rs.getString(3))));
				}
				if(rs != null){
					rs.close();
				}
				ps.close();
				connection.close();
			} catch (SQLException e) {
			e.printStackTrace();
		}	
	}



}
