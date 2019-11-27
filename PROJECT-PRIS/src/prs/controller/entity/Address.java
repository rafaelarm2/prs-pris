package prs.controller.entity;

public class Address {
	private int id;
	private String address;
	private String neighborhood;
	private String zipcode;
	private int number;
	private String comp;
	private String city;
	private String state;
	
	public Address(String address, String neighborhood, String zipcode, int number, String comp, String city, String state) {
		this.address = address;
		this.neighborhood = neighborhood;
		this.zipcode = zipcode;
		this.number = number;
		this.comp = comp;
		this.city = city;
		this.state = state;
	}

	public Address() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getComp() {
		return comp;
	}

	public void setComp(String comp) {
		this.comp = comp;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
	
}
