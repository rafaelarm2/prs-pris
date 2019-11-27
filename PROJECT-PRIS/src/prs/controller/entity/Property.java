package prs.controller.entity;

public abstract class Property {
	protected int id;
	protected int type;
	protected Address address;
	protected double priceRental;
	protected int numberRooms;
	protected int numberBathroom;
	protected double area;
	protected int parkingSpace;
	protected String description;
	protected String cpfTenant;
	
	
	public Property(int id, int type, Address address, double priceRental, int numberRooms, int numberBathroom, double area,
			int parkingSpace, String description, String cpfTenant) {
		this.id = id;
		this.type = type;
		this.address = address;
		this.priceRental = priceRental;
		this.numberRooms = numberRooms;
		this.numberBathroom = numberBathroom;
		this.area = area;
		this.parkingSpace = parkingSpace;
		this.description = description;
		this.cpfTenant = cpfTenant;
	}


	public Property() {
		
	}


	public Property(int type, Address address, double priceRental, int numberRooms, int numberBathroom, double area,
			int parkingSpace, String description, String cpfTenant) {
		this.type = type;
		this.address = address;
		this.priceRental = priceRental;
		this.numberRooms = numberRooms;
		this.numberBathroom = numberBathroom;
		this.area = area;
		this.parkingSpace = parkingSpace;
		this.description = description;
		this.cpfTenant = cpfTenant;
	}
	

	public Property(int type, Address address, double priceRental, int numberRooms, int numberBathroom, double area,
			int parkingSpace, String description) {
		this.type = type;
		this.address = address;
		this.priceRental = priceRental;
		this.numberRooms = numberRooms;
		this.numberBathroom = numberBathroom;
		this.area = area;
		this.parkingSpace = parkingSpace;
		this.description = description;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public double getPriceRental() {
		return priceRental;
	}


	public void setPriceRental(double priceRental) {
		this.priceRental = priceRental;
	}


	public int getNumberRooms() {
		return numberRooms;
	}


	public void setNumberRooms(int numberRooms) {
		this.numberRooms = numberRooms;
	}


	public int getNumberBathroom() {
		return numberBathroom;
	}


	public void setNumberBathroom(int numberBathroom) {
		this.numberBathroom = numberBathroom;
	}


	public double getArea() {
		return area;
	}


	public void setArea(double area) {
		this.area = area;
	}


	public int getParkingSpace() {
		return parkingSpace;
	}


	public void setParkingSpace(int parkingSpace) {
		this.parkingSpace = parkingSpace;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCpfTenant() {
		return cpfTenant;
	}


	public void setCpfTenant(String cpfTenant) {
		this.cpfTenant = cpfTenant;
	}
	
	
	

}
