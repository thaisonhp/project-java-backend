package com.javaweb.model;

public class BuildingDTO {
	private String name ; 
	private String address ; // doung , phuong , quan => districtId ; 
	private int numberOfBasement;
	private String nameOfManager ; 
	private String phoneNumber ; 
	private double floorArea ; 
	private String rentedArea ; 
	private double emptyArea ; 
	private double rentPrice ; 
	private double serviceFee ; 
	private double MGfee ;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(int numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public String getNameOfManager() {
		return nameOfManager;
	}
	public void setNameOfManager(String nameOfManager) {
		this.nameOfManager = nameOfManager;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public double getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(double floorArea) {
		this.floorArea = floorArea;
	}
	public String getRentedArea() {
		return rentedArea;
	}
	public void setRentedArea(String rentedArea) {
		this.rentedArea = rentedArea;
	}
	public double getEmptyArea() {
		return emptyArea;
	}
	public void setEmptyArea(double emptyArea) {
		this.emptyArea = emptyArea;
	}
	public double getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(double rentPrice) {
		this.rentPrice = rentPrice;
	}
	public double getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}
	public double getMGfee() {
		return MGfee;
	}
	public void setMGfee(double mGfee) {
		MGfee = mGfee;
	} 
	
	
	
	
	
}
