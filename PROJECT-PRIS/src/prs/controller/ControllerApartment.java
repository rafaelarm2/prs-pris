package prs.controller;

import java.util.List;

import prs.controller.entity.Apartment;
import prs.data.IRepositoryApartment;

public class ControllerApartment {
	private IRepositoryApartment rApartment;

	public ControllerApartment(IRepositoryApartment rApartment) {
		this.rApartment = rApartment;
	}
	
	public int getNextId() {
		return rApartment.getNextId();
	}

	public Apartment read(int id) {
		return rApartment.read(id);
	}

	public boolean update(Apartment a, int id, int idaddress) {
		return rApartment.update(a, id, idaddress);
	}

	public boolean delete(int id) {
		return rApartment.delete(id);
	}

	public boolean create(Apartment a) {
		return rApartment.create(a);
	}

	public List<Apartment> getList() {
		return rApartment.getList();
	}
	
}
