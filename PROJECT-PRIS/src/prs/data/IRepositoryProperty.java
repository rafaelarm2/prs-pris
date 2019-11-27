package prs.data;

public interface IRepositoryProperty {
	
	int getType(int id);

	int getIdAddress(int id);

	boolean delete(int id);

	boolean rental(int id, String cpf);

	boolean rented(int id);

}
