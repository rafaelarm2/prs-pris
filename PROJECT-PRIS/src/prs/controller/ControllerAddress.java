package prs.controller;

import prs.data.IRepositoryAddress;

public class ControllerAddress {
	private IRepositoryAddress rAddress;

	public ControllerAddress(IRepositoryAddress rAddress) {
		this.rAddress = rAddress;
	}
	
	public int getNextId() {
		return rAddress.getNextId();
	}

	public boolean delete(int id) {
		return rAddress.delete(id);
	}
	
	

}
