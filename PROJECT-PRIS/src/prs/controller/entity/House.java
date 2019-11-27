package prs.controller.entity;

public class House extends Property {
	private int numberFloor;

	public House(Address address, double priceRental, int numberRooms, int numberBathroom, double area,
			int parkingSpace, String description, int numberFloor, String cpf_tenant) {
		super(1, address, priceRental, numberRooms, numberBathroom, area, parkingSpace, description, cpf_tenant);
		this.numberFloor = numberFloor;
	}
	
	public House(int id, Address address, double priceRental, int numberRooms, int numberBathroom, double area,
			int parkingSpace, String description, int numberFloor, String cpf_tenant) {
		super(id, 1, address, priceRental, numberRooms, numberBathroom, area, parkingSpace, description, cpf_tenant);
		this.numberFloor = numberFloor;
	}

	public House() {
		super();
	}

	public House(int typeProperty, Address address, double priceRental, int numberRooms, int numberBathroom, int parkingSpace, double area, String description) {
		super(1, address, priceRental, numberRooms, numberBathroom, area, parkingSpace, description, null);
		
	}

	public int getNumberFloor() {
		return numberFloor;
	}

	public void setNumberFloor(int numberFloor) {
		this.numberFloor = numberFloor;
	}
	
	
	
	
	
}
