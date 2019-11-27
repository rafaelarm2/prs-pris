package prs.data;

import java.util.List;

import prs.controller.entity.House;

public interface IRepositoryHouse {

	boolean create(House h);
	
	House read(int id);
	
	List<House> getList();
	
	boolean update(House a, int id, int idaddress);
	
	boolean delete(int id);
	
	int getNextId();
	
}
