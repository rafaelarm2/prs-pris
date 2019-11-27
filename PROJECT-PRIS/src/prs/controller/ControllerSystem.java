package prs.controller;

import prs.data.IRepositoryAddress;
import prs.data.IRepositoryHouse;
import prs.data.IRepositoryProperty;
import prs.data.IRepositoryUser;
import prs.data.IRepositoryApartment;
import prs.controller.entity.House;
import prs.controller.entity.User;

import java.util.List;
import prs.controller.entity.Apartment;

public class ControllerSystem {
	private ControllerApartment cApartment;
	private ControllerHouse cHouse;
	private ControllerAddress cAddress;
	private ControllerProperty cProperty;
	private ControllerUser cUser;
	
	public ControllerSystem(IRepositoryApartment rApartment, IRepositoryHouse rHouse, IRepositoryAddress rAddress,
			IRepositoryProperty rProperty, IRepositoryUser rUser) {
		super();
		this.cApartment = new ControllerApartment(rApartment);
		this.cHouse = new ControllerHouse(rHouse);
		this.cAddress = new ControllerAddress(rAddress);
		this.cProperty = new ControllerProperty(rProperty);
		this.cUser = new ControllerUser(rUser);
	}
	
	public int getNextIdHouse() {
		return cHouse.getNextId();
	}

	public House readHouse(int id) {
		return cHouse.read(id);
	}

	public boolean updateHouse(House h, int id, int idaddress) {
		return cHouse.update(h, id, idaddress);
	}

	public boolean deleteHouse(int id) {
		return cHouse.delete(id);
	}

	public boolean createHouse(House h) {
		return cHouse.create(h);
	}

	public List<House> getListHouse() {
		return cHouse.getList();
	}
	
	public int getNextIdApartment() {
		return cApartment.getNextId();
	}

	public Apartment readApartment(int id) {
		return cApartment.read(id);
	}

	public boolean updateApartment(Apartment a, int id, int idaddress) {
		return cApartment.update(a, id, idaddress);
	}

	public boolean deleteApartment(int id) {
		return cApartment.delete(id);
	}

	public boolean createApartment(Apartment a) {
		return cApartment.create(a);
	}

	public List<Apartment> getListApartment() {
		return cApartment.getList();
	}
	
	public int getNextIdAddress() {
		return cAddress.getNextId();
	}
	
	public int getTypeProperty(int id) {
		return cProperty.getType(id);
	}

	public int getIdAddress(int id) {
		return cProperty.getIdAddress(id);
	}

	public boolean deleteAddress(int id) {
		return cAddress.delete(id);
		
	}

	public boolean deleteProperty(int id) {
		return cProperty.delete(id);
		
	}

	public boolean createUser(User user) {
		return cUser.create(user);
	}

	public boolean isValidUser(User user) {
		return cUser.isValid(user);
	}

	public boolean rental(int id, String cpf) {
		return cProperty.rental(id, cpf);
	}

	public boolean rented(int id) {
		return cProperty.rented(id);
	}


}
