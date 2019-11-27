package prs.ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.json.JSONException;

import cep.Cep;
import prs.controller.ControllerSystem;
import prs.data.DAOAddress;
import prs.data.DAOApartment;
import prs.data.DAOHouse;
import prs.data.DAOProperty;
import prs.data.DAOUser;
import prs.data.IRepositoryAddress;
import prs.data.IRepositoryApartment;
import prs.data.IRepositoryHouse;
import prs.data.IRepositoryProperty;
import prs.data.IRepositoryUser;
import prs.controller.entity.Address;
import prs.controller.entity.Apartment;
import prs.controller.entity.House;
import prs.controller.entity.Property;
import prs.controller.entity.User;

public class Interface {
	ControllerSystem cSys;
	IRepositoryAddress rAddress;
	IRepositoryHouse rHouse;
	IRepositoryApartment rApartment;
	IRepositoryProperty rProperty;
	IRepositoryUser rUser;
	User currentUser;

	/* CONSTRUCTOR */
	public Interface() throws SQLException {
		this.rProperty = new DAOProperty();
		this.rAddress = new DAOAddress();
		this.rHouse = new DAOHouse(rAddress);
		this.rApartment = new DAOApartment(rAddress);
		this.rUser = new DAOUser();
		this.cSys = new ControllerSystem(rApartment, rHouse, rAddress, rProperty, rUser);
		this.currentUser = null;
	}

	/* LOGIN OR REGISTER */
	public boolean openHome(Scanner keyboardReading) {
		int op;

		System.out.println("1. Logar ");
		System.out.println("2. Novo cadastro ");

		System.out.print("Escolha a opção: ");
		try {
			op = Integer.parseInt(keyboardReading.nextLine());
		} catch (NumberFormatException e) {
			// IF THE PARSE DOESNT WORK OP = 0 (TO CREATE A NEW USER)
			op = 0;
		}

		// ONLY IF OP == 1 -> LOGIN
		if (op == 1) {
			this.loginUser(keyboardReading);
		} else {
			this.createUser(keyboardReading);
		}

		return true;
	}

	/* REGISTER NEW USER */
	private boolean createUser(Scanner keyboardReading) {
		User user = new User();

		System.out.println("\nCadastrar");
		System.out.print("CPF: ");
		user.setCpf(keyboardReading.nextLine());
		System.out.print("Senha: ");
		user.setPassword(keyboardReading.nextLine());

		if (cSys.createUser(user)) {
			this.currentUser = user;
			System.out.println("Cadastrado com sucesso. \n");

			return true;
		} else {
			System.out.println("CPF ja existe na base de dados. \n");
			this.openHome(keyboardReading);
			return false;
		}
	}

	/* LOGIN (USER MUST BE PREVIOUSLY CREATED) */
	public boolean loginUser(Scanner keyboardReading) {
		User user = new User();

		System.out.println("\nLogin");
		System.out.print("CPF: ");
		user.setCpf(keyboardReading.nextLine());
		System.out.print("Senha: ");
		user.setPassword(keyboardReading.nextLine());

		if (cSys.isValidUser(user)) {
			this.currentUser = user;
			return true;
		} else {
			System.out.println("\nLogin invalido.\n");
			this.openHome(keyboardReading);
		}

		return false;

	}

	/* LIST OF FUNCTIONS */
	public int openMenu(Scanner keyboardReading) {
		int optionMenu;

		System.out.println("\nMenu Principal");
		System.out.println("1. Cadastro de propriedade ");
		System.out.println("2. Lista propriedades ");
		System.out.println("3. Alterar propriedade ");
		System.out.println("4. Excluir propriedade ");
		System.out.println("5. Alugar ");
		System.out.println("0. Sair ");

		System.out.print("Escolha a opção: ");
		try {
			optionMenu = Integer.parseInt(keyboardReading.nextLine());
		} catch (NumberFormatException e) {
			optionMenu = 0;
		}
		return optionMenu;

	}

	/* CREATE A NEW PROPERTY - APARTMENT OR HOUSE */
	public boolean createProperty(Scanner keyboardReading) {
		System.out.println("\n NOVA PROPRIEDADE \n");

		Property newProperty;

		Address newAddress = null;

		System.out.print("CEP: ");

		try {
			Cep cep = new Cep();
			// CALL FUNCTION TO FIND CEP USING VIA CEP AND RETURN THE ADDRESS OBJECT
			String cepStr = keyboardReading.nextLine();
			cepStr.replaceAll("-", "");
			newAddress = cep.findCEP(cepStr);

		} catch (IOException e) {
			// ILLEGAL FORMAT
			System.out.println("CEP invalido.\n");
			return false;

		} catch (JSONException e) {
			// NOT FOUND
			System.out.println("CEP nao encontrado.\n");
			return false;

		}

		// ADDRESS BY VIA CEP
		System.out.println(newAddress.getAddress() + ", bairro " + newAddress.getNeighborhood() + ", "
				+ newAddress.getCity() + " - " + newAddress.getState());

		// GET NUMBER PROPERTY AND COMPLEMENT
		System.out.print("Numero: ");
		try {
			int number = Integer.parseInt(keyboardReading.nextLine());
			if (number > 0) {
				newAddress.setNumber(number);
			} else {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			// WHEN IS NOT POSSIBLE TO CONVERT
			System.out.println("Numero invalido.\n");
			return false;
		}

		System.out.print("Complemento: ");
		newAddress.setComp(keyboardReading.nextLine());

		// SELECT THE PROPERTY TYPE - HOUSE OR APARTMENT
		System.out.print("Tipo de propriedade (1.Casa  2.Apartamento): ");
//		System.out.println("1. Casa: ");
//		System.out.println("2. Apartamento: ");

		int typeProperty = 0;
		double pricerental, area;
		int room, bathroom, parking;
		String description;
		try {
			typeProperty = Integer.parseInt(keyboardReading.nextLine());

			System.out.print("Preco do aluguel: ");
			pricerental = Double.parseDouble(keyboardReading.nextLine());
			if (pricerental <= 0.0) {
				throw new NumberFormatException();
			}

			System.out.print("Quartos: ");
			room = Integer.parseInt(keyboardReading.nextLine());
			if (room <= 0) {
				throw new NumberFormatException();
			}

			System.out.print("Banheiros: ");
			bathroom = Integer.parseInt(keyboardReading.nextLine());
			if (bathroom <= 0) {
				throw new NumberFormatException();
			}

			System.out.print("Area (m2): ");
			area = Double.parseDouble(keyboardReading.nextLine());
			if (area <= 0.0) {
				throw new NumberFormatException();
			}

			System.out.print("Vagas de estacionamento: ");
			parking = Integer.parseInt(keyboardReading.nextLine());
			if (parking < 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			// WHEN IS NOT POSSIBLE TO CONVERT
			System.out.println("Numero invalido.\n");
			return false;
		}

		System.out.print("Descricao: ");
		description = keyboardReading.nextLine();

		// GET INFORMATION ABOUT THE HOUSE OR APARTMENT
		if (typeProperty == 1) {
			newProperty = new House(typeProperty, newAddress, pricerental, room, bathroom, parking, area, description);

			try {
				int floor;
				System.out.print("Numero de andares: ");
				floor = Integer.parseInt(keyboardReading.nextLine());
				if (floor > 0) {
					((House) newProperty).setNumberFloor(floor);
				} else {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				// WHEN IS NOT POSSIBLE TO CONVERT
				System.out.println("Numero invalido.\n");
				return false;
			}

			// CALL FUNCTION CREATE TO TYPE HOUSE
			cSys.createHouse((House) newProperty);
			System.out.println("\nCadastro realizado com sucesso.");
			return true;

		} else {
			newProperty = new Apartment(typeProperty, newAddress, pricerental, room, bathroom, parking, area,
					description);

			try {

				System.out.print("Taxa de condominio: ");
				double fee = Double.parseDouble(keyboardReading.nextLine());
				if (fee >= 0.0) {
					((Apartment) newProperty).setCondominiumFee(fee);
				} else {
					throw new NumberFormatException();
				}

				System.out.println("Duplex: ");
				System.out.println("1. Sim: ");
				System.out.println("2. Nao: ");

				// CHECK ISDUPLEX TRUE OR FALSE
				int isDuplex = Integer.parseInt(keyboardReading.nextLine());

				if (isDuplex == 1) {
					((Apartment) newProperty).setDuplex(true);
				} else {
					((Apartment) newProperty).setDuplex(false);
				}
			} catch (NumberFormatException e) {
				// WHEN IS NOT POSSIBLE TO CONVERT
				System.out.println("Numero invalido.\n");
				return false;
			}

			// CALL FUNCTION CREATE TO TYPE APARTMENT
			cSys.createApartment((Apartment) newProperty);
			System.out.println("\nCadastro realizado com sucesso.");

			return true;
		}

	}

	/* GET LIST OF ALL PROPERTIES - APARTMENT OR HOUSE */
	public void getListProperty() {

		// GET LIST APARTMENT
		System.out.println("\nApartamentos: \n");
		if (!cSys.getListApartment().isEmpty()) {
			for (int i = 0; i < cSys.getListApartment().size(); i++) {

				if (cSys.getListApartment().get(i).getCpfTenant() == null) {
					System.out.print("Disponivel ");
				} else {
					System.out.print("Indisponivel ");
				}

				System.out.print("ID:" + cSys.getListApartment().get(i).getId() + " ");
				System.out.print(cSys.getListApartment().get(i).getDescription() + " ");
				System.out.print(cSys.getListApartment().get(i).getArea() + " ");
				System.out.print(cSys.getListApartment().get(i).getNumberRooms() + " ");
				System.out.print(cSys.getListApartment().get(i).getNumberBathroom() + " ");
				System.out.print(cSys.getListApartment().get(i).getPriceRental() + " ");
				System.out.print(cSys.getListApartment().get(i).getCondominiumFee() + " ");
				System.out.print(cSys.getListApartment().get(i).getParkingSpace() + " ");

				if (cSys.getListApartment().get(i).isDuplex()) {
					System.out.print("Duplex ");
				} else {
					System.out.print("Comum ");
				}

				System.out.print(cSys.getListApartment().get(i).getAddress().getAddress() + " ");
				System.out.print(cSys.getListApartment().get(i).getAddress().getNeighborhood() + " ");
				System.out.print(cSys.getListApartment().get(i).getAddress().getZipcode() + " ");
				System.out.print(cSys.getListApartment().get(i).getAddress().getNumber() + " ");
				System.out.print(cSys.getListApartment().get(i).getAddress().getComp() + " ");
				System.out.print(cSys.getListApartment().get(i).getAddress().getCity() + " ");
				System.out.println(cSys.getListApartment().get(i).getAddress().getState());
			}
			System.out.println("");
		} else {
			System.out.println(" Nenhum apartamento cadastrado.\n");
		}

		// GET LIST HOUSE
		System.out.println("Casas: \n");
		if (!cSys.getListHouse().isEmpty()) {
			for (int i = 0; i < cSys.getListHouse().size(); i++) {

				if (cSys.getListHouse().get(i).getCpfTenant() == null) {
					System.out.print("Disponivel ");
				} else {
					System.out.print("Indisponivel ");
				}

				System.out.print("ID:" + cSys.getListHouse().get(i).getId() + " ");
				System.out.print(cSys.getListHouse().get(i).getDescription() + " ");
				System.out.print(cSys.getListHouse().get(i).getArea() + " ");
				System.out.print(cSys.getListHouse().get(i).getNumberRooms() + " ");
				System.out.print(cSys.getListHouse().get(i).getNumberBathroom() + " ");
				System.out.print(cSys.getListHouse().get(i).getPriceRental() + " ");
				System.out.print(cSys.getListHouse().get(i).getNumberFloor() + " ");
				System.out.print(cSys.getListHouse().get(i).getParkingSpace() + " ");
				System.out.print(cSys.getListHouse().get(i).getAddress().getAddress() + " ");
				System.out.print(cSys.getListHouse().get(i).getAddress().getNeighborhood() + " ");
				System.out.print(cSys.getListHouse().get(i).getAddress().getZipcode() + " ");
				System.out.print(cSys.getListHouse().get(i).getAddress().getNumber() + " ");
				System.out.print(cSys.getListHouse().get(i).getAddress().getComp() + " ");
				System.out.print(cSys.getListHouse().get(i).getAddress().getCity() + " ");
				System.out.println(cSys.getListHouse().get(i).getAddress().getState());
			}
			System.out.println("");
		} else {
			System.out.println(" Nenhuma casa cadastrada.\n");
		}

	}

	/* UPDATE PROPERTY - APARTMENT OR HOUSE */
	public boolean updateProperty(Scanner keyboardReading) {

		System.out.println("\n ALTERAR PROPRIEDADE \n");

		System.out.println("Selecione uma propriedade para alterar: ");

		// GET ALL PROPERTIES
		this.getListProperty();

		Property newProperty;

		int idProperty = 0;
		// SELECT PROPERTY TO UPDATE
		try {
			System.out.print("Codigo da propriedade: ");
			idProperty = Integer.parseInt(keyboardReading.nextLine());
		} catch (NumberFormatException e) {
			// WHEN IS NOT POSSIBLE TO CONVERT
			System.out.println(" Numero invalido.\n");
			return false;
		}

		// TYPE - APARTMENT OR HOUSE
		int typeProperty = cSys.getTypeProperty(idProperty);
		// CHECK IF EXISTS
		if (typeProperty == 0) {
			System.out.println(" Propriedade nao existe.\n");
			return false;
		}

		// GET THE CORRECT ID ADDRESS TO UPDATE
		int idAddress = cSys.getIdAddress(idProperty);

		// GET THE ADDRESS THROUGH THE CEP USING VIA CEP API
		Address newAddress = null;

		System.out.print("CEP: ");

		try {
			Cep cep = new Cep();
			// CALL FUNCTION TO FIND CEP USING VIA CEP AND RETURN THE ADDRESS OBJECT
			String cepStr = keyboardReading.nextLine();
			cepStr.replaceAll("-", "");
			newAddress = cep.findCEP(cepStr);

		} catch (IOException e) {
			// ILLEGAL FORMAT
			System.out.println("\nCEP invalido.");
			return false;

		} catch (JSONException e) {
			// NOT FOUND
			System.out.println("\nCEP nao encontrado.");
			return false;

		}

		// ADDRESS BY VIA CEP
		System.out.println(newAddress.getAddress() + ", bairro " + newAddress.getNeighborhood() + ", "
				+ newAddress.getCity() + " - " + newAddress.getState());

		// GET NUMBER PROPERTY AND COMPLEMENT
		System.out.print("Numero: ");
		try {
			int number = Integer.parseInt(keyboardReading.nextLine());
			if (number > 0) {
				newAddress.setNumber(number);
			} else {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			// WHEN IS NOT POSSIBLE TO CONVERT
			System.out.println("\nNumero invalido.");
			return false;
		}

		System.out.print("Complemento: ");
		newAddress.setComp(keyboardReading.nextLine());

		double pricerental, area;
		int room, bathroom, parking;
		String description;
		try {
			System.out.print("Preco do aluguel: ");
			pricerental = Double.parseDouble(keyboardReading.nextLine());
			if (pricerental <= 0.0) {
				throw new NumberFormatException();
			}

			System.out.print("Quartos: ");
			room = Integer.parseInt(keyboardReading.nextLine());
			if (room <= 0) {
				throw new NumberFormatException();
			}

			System.out.print("Banheiros: ");
			bathroom = Integer.parseInt(keyboardReading.nextLine());
			if (bathroom <= 0) {
				throw new NumberFormatException();
			}

			System.out.print("Area (m2): ");
			area = Double.parseDouble(keyboardReading.nextLine());
			if (area <= 0.0) {
				throw new NumberFormatException();
			}

			System.out.print("Vagas de estacionamento: ");
			parking = Integer.parseInt(keyboardReading.nextLine());
			if (parking < 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			// WHEN IS NOT POSSIBLE TO CONVERT
			System.out.println("\nNumero invalido.");
			return false;
		}

		System.out.print("Descricao: ");
		description = keyboardReading.nextLine();

		// GET INFORMATION ABOUT THE HOUSE OR APARTMENT
		if (typeProperty == 1) {
			// GET PROPERTY HOUSE OBJECT TO UPDATE

			// CREATE A NEW OBJECT TO REPLACE THE OLDER
			newProperty = new House(typeProperty, newAddress, pricerental, room, bathroom, parking, area, description);

			try {
				int floor;
				System.out.print("Numero de andares: ");
				floor = Integer.parseInt(keyboardReading.nextLine());
				if (floor > 0) {
					((House) newProperty).setNumberFloor(floor);
				} else {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				// WHEN IS NOT POSSIBLE TO CONVERT
				System.out.println("\nNumero invalido.");
				return false;
			}

			System.out.print("Descricao: ");
			((House) newProperty).setDescription(keyboardReading.nextLine());

			// CALL FUNCTION TO UPDATE BOTH ADDRESS AND PROPERTY
			cSys.updateHouse(((House) newProperty), idProperty, idAddress);
			System.out.println("\nAlterada com sucesso.");
			return true;
		} else {

			// CREATE A NEW OBJECT TO REPLACE THE OLDER
			newProperty = new Apartment(typeProperty, newAddress, pricerental, room, bathroom, parking, area,
					description);

			try {

				System.out.print("Taxa de condominio: ");
				double fee = Double.parseDouble(keyboardReading.nextLine());
				if (fee >= 0.0) {
					((Apartment) newProperty).setCondominiumFee(fee);
				} else {
					throw new NumberFormatException();
				}

				System.out.println("Duplex: ");
				System.out.println("1. Sim ");
				System.out.println("2. Nao ");

				// CHECK ISDUPLEX TRUE OR FALSE
				int isDuplex = Integer.parseInt(keyboardReading.nextLine());

				if (isDuplex == 1) {
					((Apartment) newProperty).setDuplex(true);
				} else {
					((Apartment) newProperty).setDuplex(false);
				}
			} catch (NumberFormatException e) {
				// WHEN IS NOT POSSIBLE TO CONVERT
				System.out.println("\nNumero invalido.");
				return false;
			}

			// CALL FUNCTION TO UPDATE BOTH ADDRESS AND PROPERTY
			cSys.updateApartment(((Apartment) newProperty), idProperty, idAddress);
			System.out.println("\nAlterada com sucesso.");

			return true;
		}

	}

	/* DELETE PROPERTY - APARTMENT OR HOUSE */
	public boolean deleteProperty(Scanner keyboardReading) {

		System.out.println("\n DELETAR PROPRIEDADE \n");

		System.out.println("Selecione uma propriedade para deletar: ");

		// GET ALL PROPERTIES
		this.getListProperty();

		int idProperty = 0;

		// SELECT PROPERTY TO REMOVE
		try {
			System.out.print("Codigo da propriedade: ");
			idProperty = Integer.parseInt(keyboardReading.nextLine());
		} catch (NumberFormatException e) {
			// WHEN IS NOT POSSIBLE TO CONVERT
			System.out.println("\nNumero invalido.");
			return false;
		}

		// TYPE - APARTMENT OR HOUSE
		int typeProperty = cSys.getTypeProperty(idProperty);

		// CHECK IF EXISTS
		if (typeProperty == 0) {
			System.out.println("\n Propriedade nao existe.");
			return false;
		}

		// GET THE ADDRESS
		int idAddress = cSys.getIdAddress(idProperty);

		// DELETE 3 DIFFERENT OBJECTS ON DB
		if (typeProperty == 1) {
			cSys.deleteHouse(idProperty);
		} else {
			cSys.deleteApartment(idProperty);
		}
		cSys.deleteProperty(idProperty);
		cSys.deleteAddress(idAddress);

		System.out.println("\nRemovida com sucesso.");
		return true;

	}

	public boolean rentalProperty(Scanner keyboardReading) {

		System.out.println("\n ALUGAR PROPRIEDADE \n");

		System.out.println("Selecione uma propriedade para alugar: ");

		// GET ALL PROPERTIES
		this.getListProperty();

		int idProperty = 0;
		// SELECT PROPERTY TO RENTAL
		try {
			System.out.print("Codigo da propriedade: ");
			idProperty = Integer.parseInt(keyboardReading.nextLine());
		} catch (NumberFormatException e) {
			// WHEN IS NOT POSSIBLE TO CONVERT
			System.out.println("\nNumero invalido.");
			return false;
		}

		// TYPE - APARTMENT OR HOUSE
		int type = cSys.getTypeProperty(idProperty);

		// CHECK IF EXISTS
		if (type == 0) {
			System.out.println("\n Propriedade nao existe.\n");
			return false;
		}

		if (!cSys.rented(idProperty)) {
			cSys.rental(idProperty, this.currentUser.getCpf());
			System.out.println("\n Alugada com sucesso. \n");
			return true;
		} else {
			System.out.println("\n Essa propriedade nao esta disponivel. \n");
			return false;
		}
	}

}
