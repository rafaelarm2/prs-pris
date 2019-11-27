package prs.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import prs.controller.entity.Address;
import prs.controller.entity.House;

public class DAOHouse implements IRepositoryHouse {
	private IRepositoryAddress rAddress;
	private Connection connect;

	public DAOHouse(IRepositoryAddress rAddress) throws SQLException {
		//CREATE A CONNECTION
		connect = ConnectionFactory.getConnection();
		this.rAddress = rAddress;

	}

	@Override
	public boolean create(House h) {
		try {
			PreparedStatement stmt = connect.prepareStatement(
					"INSERT INTO TB_ADDRESS(TB_ADDRESS.address, TB_ADDRESS.neighborhood, "
					+ "TB_ADDRESS.zipcode, TB_ADDRESS.number, TB_ADDRESS.comp, TB_ADDRESS.city, TB_ADDRESS.state) "
					+ "VALUES(?,?,?,?,?,?,?);");
			stmt.setString(1, h.getAddress().getAddress());
			stmt.setString(2, h.getAddress().getNeighborhood());
			stmt.setString(3, h.getAddress().getZipcode());
			stmt.setString(4, Integer.toString(h.getAddress().getNumber()));
			stmt.setString(5, h.getAddress().getComp());
			stmt.setString(6, h.getAddress().getCity());
			stmt.setString(7, h.getAddress().getState());

			stmt.execute();
			
			stmt = connect.prepareStatement(
					"INSERT INTO TB_PROPERTY(TB_PROPERTY.id_address, TB_PROPERTY.price_rental, "
					+ "TB_PROPERTY.number_rooms, TB_PROPERTY.number_bathroom, TB_PROPERTY.area, "
					+ "TB_PROPERTY.parking, TB_PROPERTY.description, TB_PROPERTY.type_property) "
					+ "VALUES(?,?,?,?,?,?,?,?);");
			stmt.setString(1, Integer.toString(rAddress.getNextId()));
			stmt.setString(2, Double.toString(h.getPriceRental()));
			stmt.setString(3, Integer.toString(h.getNumberRooms()));
			stmt.setString(4, Integer.toString(h.getNumberBathroom()));
			stmt.setString(5, Double.toString(h.getArea()));
			stmt.setString(6, Integer.toString(h.getParkingSpace()));
			stmt.setString(7, h.getDescription());
			stmt.setString(8, "1");
			
			stmt.execute();
			
			stmt = connect.prepareStatement(
					"INSERT INTO TB_HOUSE(TB_HOUSE.id_property, TB_HOUSE.number_floor "
					+ ") VALUES(?,?);");
			stmt.setString(1, Integer.toString(this.getNextId()));
			stmt.setString(2, Integer.toString(h.getNumberFloor()));
			
			stmt.execute();
			
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public House read(int id) {
		House h = null;
		try {
			PreparedStatement stmt = connect.prepareStatement("select TB_PROPERTY.id_property, TB_PROPERTY.type_property, "
					+ "TB_PROPERTY.price_rental, TB_PROPERTY.number_rooms, TB_PROPERTY.number_bathroom, "
					+ "TB_PROPERTY.area, TB_PROPERTY.parking, TB_PROPERTY.description, TB_HOUSE.number_floor, "
					+ "TB_ADDRESS.id_address, TB_ADDRESS.address, TB_ADDRESS.neighborhood, TB_ADDRESS.zipcode, "
					+ "TB_ADDRESS.number, TB_ADDRESS.comp, TB_ADDRESS.city, TB_ADDRESS.state " + 
					"from TB_PROPERTY, TB_ADDRESS, TB_HOUSE " + 
					"where (TB_PROPERTY.id_property = TB_HOUSE.id_property and " + 
					"    TB_PROPERTY.id_address = TB_ADDRESS.id_address);");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				h = new House();
				Address ad = new Address();
				h.setId(rs.getInt("id_property"));
				h.setType(1);
				h.setPriceRental(rs.getDouble("price_rental"));
				h.setNumberRooms(rs.getInt("number_rooms"));
				h.setNumberBathroom(rs.getInt("number_bathroom"));
				h.setArea(rs.getDouble("area"));
				h.setParkingSpace(rs.getInt("parking"));
				h.setDescription(rs.getString("description"));
				h.setType(1);
				h.setNumberFloor(rs.getInt("number_floor"));
				ad.setId(rs.getInt("id_address"));
				ad.setAddress(rs.getString("address"));
				ad.setNeighborhood(rs.getString("neighborhood"));
				ad.setZipcode(rs.getString("zipcode"));
				ad.setNumber(rs.getInt("number"));
				ad.setComp(rs.getString("comp"));
				ad.setCity(rs.getString("city"));
				ad.setState(rs.getString("state"));

				h.setAddress(ad);
				return h;
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return h;
	}

	@Override
	public List<House> getList() {
		List<House> list = new ArrayList<House>();

		try {
			PreparedStatement stmt = connect.prepareStatement("select TB_PROPERTY.id_property, "
					+ "TB_PROPERTY.price_rental, TB_PROPERTY.number_rooms, TB_PROPERTY.number_bathroom, "
					+ "TB_PROPERTY.area, TB_PROPERTY.parking, TB_PROPERTY.description, "
					+ "TB_PROPERTY.cpf_tenant, TB_HOUSE.number_floor, "
					+ "TB_ADDRESS.id_address, TB_ADDRESS.address, TB_ADDRESS.neighborhood, TB_ADDRESS.zipcode, "
					+ "TB_ADDRESS.number, TB_ADDRESS.comp, TB_ADDRESS.city, TB_ADDRESS.state " + 
					"from TB_PROPERTY, TB_ADDRESS, TB_HOUSE " + 
					"where (TB_PROPERTY.id_property = TB_HOUSE.id_property and " + 
					"    TB_PROPERTY.id_address = TB_ADDRESS.id_address);");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				House h = new House();
				Address ad = new Address();
				h.setId(rs.getInt("id_property"));
				h.setType(1);
				h.setPriceRental(rs.getDouble("price_rental"));
				h.setNumberRooms(rs.getInt("number_rooms"));
				h.setNumberBathroom(rs.getInt("number_bathroom"));
				h.setArea(rs.getDouble("area"));
				h.setParkingSpace(rs.getInt("parking"));
				h.setDescription(rs.getString("description"));
				h.setNumberFloor(rs.getInt("number_floor"));
				h.setCpfTenant(rs.getString("cpf_tenant"));
				ad.setId(rs.getInt("id_address"));
				ad.setAddress(rs.getString("address"));
				ad.setNeighborhood(rs.getString("neighborhood"));
				ad.setZipcode(rs.getString("zipcode"));
				ad.setNumber(rs.getInt("number"));
				ad.setComp(rs.getString("comp"));
				ad.setCity(rs.getString("city"));
				ad.setState(rs.getString("state"));

				h.setAddress(ad);
				
				list.add(h);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public boolean update(House h, int id, int idaddress) {
		try {
			PreparedStatement stmt = connect.prepareStatement(
					"UPDATE TB_ADDRESS SET address=?, neighborhood=?, zipcode=?, number=?, comp=?, city=?, state=? "
					+ "WHERE id_address=?");
			stmt.setString(1, h.getAddress().getAddress());
			stmt.setString(2, h.getAddress().getNeighborhood());
			stmt.setString(3, h.getAddress().getZipcode());
			stmt.setInt(4, h.getAddress().getNumber());
			stmt.setString(5, h.getAddress().getComp());
			stmt.setString(6, h.getAddress().getCity());
			stmt.setString(7, h.getAddress().getState());

			stmt.setInt(8, idaddress);
		
			stmt.execute();
			
			stmt = connect.prepareStatement( "UPDATE TB_PROPERTY SET price_rental=?, number_rooms=?,"
					+ "number_bathroom=?, area=?, parking=?, description=? WHERE id_property=?");
			stmt.setDouble(1, h.getPriceRental());
			stmt.setInt(2, h.getNumberRooms());
			stmt.setInt(3, h.getNumberBathroom());
			stmt.setDouble(4, h.getArea());
			stmt.setInt(5, h.getParkingSpace());
			stmt.setString(6, h.getDescription());

			stmt.setInt(7, id);

			stmt.execute();
			
			stmt = connect.prepareStatement( "UPDATE TB_HOUSE SET number_floor=? "
					+ "WHERE id_property=?");
			stmt.setInt(1, h.getNumberFloor());
			stmt.setInt(2, id);

			stmt.execute();
			
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			PreparedStatement stmt = connect
					.prepareStatement("DELETE FROM TB_HOUSE WHERE (id_property=?)");
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int getNextId() {
		int proxCod = 1;
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT ID = IDENT_CURRENT('TB_PROPERTY');");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				proxCod = rs.getInt("ID");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return proxCod;
	}

}
