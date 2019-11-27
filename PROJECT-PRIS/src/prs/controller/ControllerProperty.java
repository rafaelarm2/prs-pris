package prs.controller;

import prs.data.IRepositoryProperty;

public class ControllerProperty {
	IRepositoryProperty rProperty;

	public ControllerProperty(IRepositoryProperty rProperty) {
		this.rProperty = rProperty;
	}

	public int getType(int id) {
		return rProperty.getType(id);
	}

	public int getIdAddress(int id) {
		return rProperty.getIdAddress(id);
	}

	public boolean delete(int id) {
		return rProperty.delete(id);
	}

	public boolean rental(int id, String cpf) {
		return rProperty.rental(id, cpf);
	}

	public boolean rented(int id) {
		return rProperty.rented(id);
	}


}
