package prs;


/**
 *
 * @author Rafaela Milagres Moreira
 * @version 1.0
 * 
 *          Email: moreiramrafaela@gmail.com
 * 
 * 
 * 
 */

import java.sql.SQLException;
import java.util.Scanner;

import prs.ui.Interface;

public class Main {
	static Scanner keyboardReading = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		int optionMenu;
		Interface i = new Interface();
		
		//FUNCTION HOME -> OPTIONS: LOGIN AND REGISTER
		i.openHome(keyboardReading);

		do {
			optionMenu = i.openMenu(keyboardReading);

			switch (optionMenu) {
			case (1):
				//CREATE A NEW PROPERTY -> APARTMENT AND HOUSE
				i.createProperty(keyboardReading);
				break;

			case (2):
				//LIST OF PROPERTIES INCLUDING THOSE ALREADY RENTED
				i.getListProperty();

				break;

			case (3):
				//FUNCTION UPDATE PROPERTY -> APARTMENT AND HOUSE
				i.updateProperty(keyboardReading);
			
				break;

			case (4):
				//FUNCTION DELETE PROPERTY -> APARTMENT AND HOUSE
				i.deleteProperty(keyboardReading);
			
				break;
				
			case (5):
				//FUNCTION OF RENTING PROPERTY -> APARTMENT AND HOUSE
				i.rentalProperty(keyboardReading);
			
				break;
				
			case (0):
				return;

			default:
				System.out.println("\nOpcao invalida.\n");
				
				break;
				
			}
			
		} while (optionMenu != 6);

		keyboardReading.close();
	}
	

}
