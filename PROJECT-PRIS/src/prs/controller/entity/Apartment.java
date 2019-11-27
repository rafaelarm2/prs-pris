package prs.controller.entity;

public class Apartment extends Property {

	private double condominiumFee;
	private boolean isDuplex;
	
	
	public Apartment(int id, Address address, double priceRental, int numberRooms, int numberBathroom, double area,
			int parkingSpace, String description, double condominiumFee, boolean isDuplex, String cpf_tenant) {
		super(id, 2, address, priceRental, numberRooms, numberBathroom, area, parkingSpace, description, cpf_tenant);
		this.condominiumFee = condominiumFee;
		this.isDuplex = isDuplex;
	}
	
	public Apartment(Address address, double priceRental, int numberRooms, int numberBathroom, double area,
			int parkingSpace, String description, double condominiumFee, boolean isDuplex, String cpf_tenant) {
		super(2, address, priceRental, numberRooms, numberBathroom, area, parkingSpace, description, cpf_tenant);
		this.condominiumFee = condominiumFee;
		this.isDuplex = isDuplex;
	}



	public Apartment() {
		super();
	}


	public Apartment(int typeProperty, Address address, double priceRental, int numberRooms, int numberBathroom, int parkingSpace, double area, String description) {
		super(2, address, priceRental, numberRooms, numberBathroom, area, parkingSpace, description);
		
	}

	public double getCondominiumFee() {
		return condominiumFee;
	}


	public void setCondominiumFee(double condominiumFee) {
		this.condominiumFee = condominiumFee;
	}


	public boolean isDuplex() {
		return isDuplex;
	}


	public void setDuplex(boolean isDuplex) {
		this.isDuplex = isDuplex;
	}
	
	
	
	
}
