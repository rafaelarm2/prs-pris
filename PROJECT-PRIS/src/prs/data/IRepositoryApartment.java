package prs.data;

import java.util.List;

import prs.controller.entity.Apartment;

public interface IRepositoryApartment {

	boolean create(Apartment h);
	
	Apartment read(int id);
	
	List<Apartment> getList();
	
	boolean update(Apartment a, int id, int idaddress);
	
	boolean delete(int id);
	
	int getNextId();
	
}
