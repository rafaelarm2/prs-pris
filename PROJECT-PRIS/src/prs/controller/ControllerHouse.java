package prs.controller;

import java.util.List;

import prs.controller.entity.House;
import prs.data.IRepositoryHouse;

public class ControllerHouse {
	private IRepositoryHouse rHouse;

	public ControllerHouse(IRepositoryHouse rHouse) {
		this.rHouse = rHouse;
	}
	
	public int getNextId() {
		return rHouse.getNextId();
	}

	public House read(int id) {
		return rHouse.read(id);
	}

	public boolean update(House h, int id, int idaddress) {
		return rHouse.update(h, id, idaddress);
	}

	public boolean delete(int id) {
		return rHouse.delete(id);
	}

	public boolean create(House h) {
		return rHouse.create(h);
	}

	public List<House> getList() {
		return rHouse.getList();
	}

	
}