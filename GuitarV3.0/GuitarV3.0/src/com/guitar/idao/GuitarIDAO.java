package com.guitar.idao;

import java.util.List;

import com.guitar.model.Guitar;
import com.guitar.model.GuitarSpec;

public interface GuitarIDAO {	
	public void addGuitar(String serialNumber, String backWood, String topWood, double price, String builder, String model, String type, int numStrings) throws Exception;

	public void deleteGuitar(String serialNumber) throws Exception;
	
	public void guitarAdder(String serialNumber, double price,GuitarSpec spec) throws Exception;
	
	public List<Guitar> findAll()throws Exception;
	
	public Guitar getGuitar(String serialNumber);
	
	public List<Guitar> search(GuitarSpec searchSpec) throws Exception;

	public void initializeGuitar(GuitarIDAO guitardao) throws NumberFormatException, Exception;
}
