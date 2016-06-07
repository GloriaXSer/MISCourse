package com.guitar.model;

public class Guitar {
	private String serialNumber;
	private double price;
	GuitarSpec spec;
	
	public Guitar(String serialNumber, double price, GuitarSpec spec) {
		this.setSerialNumber(serialNumber);
		this.price = price;
		this.spec = spec;
	  }
	public Guitar(){}
	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	public void setPrice(double price){
		this.price = price;
	}
	/**
	 * @return the spec
	 */
	public GuitarSpec getSpec() {
		return spec;
	}
	public void setSpec(GuitarSpec spec){
		this.spec = spec;
	}
	
}
