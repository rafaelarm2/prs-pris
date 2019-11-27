package prs.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import prs.controller.entity.Address;
import prs.controller.entity.Apartment;

public class DAOApartment implements IRepositoryApartment {
	private IRepositoryAddress rAddress;
	private Connection connect;

	public DAOApartment(IRepositoryAddress rAddress) throws SQLException {
		//CREATE A CONNECTION
		connect = ConnectionFactory.getConnection();
		this.rAddress = rAddress;

	}

	@Override
	public boolean create(Apartment a) {
		try {
			PreparedStatement stmt = connect
					.prepareStatement("INSERT INTO TB_ADDRESS(TB_ADDRESS.address, TB_ADDRESS.neighborhood, "
							+ "TB_ADDRESS.zipcode, TB_ADDRESS.number, TB_ADDRESS.comp, TB_ADDRESS.city, TB_ADDRESS.state) "
							+ "VALUES(?,?,?,?,?,?,?);");
			stmt.setString(1, a.getAddress().getAddress());
			stmt.setString(2, a.getAddress().getNeighborhood());
			stmt.setString(3, a.getAddress().getZipcode());
			stmt.setString(4, Integer.toString(a.getAddress().getNumber()));
			stmt.setString(5, a.getAddress().getComp());
			stmt.setString(6, a.getAddress().getCity());
			stmt.setString(7, a.getAddress().getState());

			stmt.execute();

			stmt = connect.prepareStatement("INSERT INTO TB_PROPERTY(TB_PROPERTY.id_address, TB_PROPERTY.price_rental, "
					+ "TB_PROPERTY.number_rooms, TB_PROPERTY.number_bathroom, TB_PROPERTY.area, "
					+ "TB_PROPERTY.parking, TB_PROPERTY.description, TB_PROPERTY.type_property)"
					+ " VALUES(?,?,?,?,?,?,?,?);");
			stmt.setString(1, Integer.toString(rAddress.getNextId()));
			stmt.setString(2, Double.toString(a.getPriceRental()));
			stmt.setString(3, Integer.toString(a.getNumberRooms()));
			stmt.setString(4, Integer.toString(a.getNumberBathroom()));
			stmt.setString(5, Double.toString(a.getArea()));
			stmt.setString(6, Integer.toString(a.getParkingSpace()));
			stmt.setString(7, a.getDescription());
			stmt.setString(8, "2");

			stmt.execute();

			stmt = connect.prepareStatement(
					"INSERT INTO TB_APARTMENT(TB_APARTMENT.id_property, TB_APARTMENT.condominium_fee, "
							+ "TB_APARTMENT.duplex) VALUES(?,?,?);");
			stmt.setString(1, Integer.toString(this.getNextId()));
			stmt.setString(2, Double.toString(a.getCondominiumFee()));
			if (a.isDuplex()) {
				stmt.setString(3, "1");
			} else {
				stmt.setString(3, "0");
			}

			stmt.execute();

			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Apartment read(int id) {
		Apartment a = null;
		try {
			PreparedStatement stmt = connect.prepareStatement("select TB_PROPERTY.id_property, "
					+ "TB_PROPERTY.price_rental, TB_PROPERTY.number_rooms, TB_PROPERTY.number_bathroom, "
					+ "TB_PROPERTY.area, TB_PROPERTY.parking, TB_PROPERTY.description, TB_PROPERTY.type_property,"
					+ " TB_APARTMENT.condominium_fee, "
					+ "TB_APARTMENT.duplex, TB_ADDRESS.id_address, TB_ADDRESS.address, TB_ADDRESS.neighborhood, "
					+ "TB_ADDRESS.zipcode, TB_ADDRESS.number, TB_ADDRESS.comp, TB_ADDRESS.city, "
					+ "TB_ADDRESS.state from TB_PROPERTY, TB_ADDRESS, TB_APARTMENT "
					+ "where (TB_PROPERTY.id_property = TB_APARTMENT.id_property and "
					+ "    TB_PROPERTY.id_address = TB_ADDRESS.id_address);");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				a = new Apartment();
				Address ad = new Address();
				a.setId(rs.getInt("id_property"));
				a.setType(2);
				a.setPriceRental(rs.getDouble("price_rental"));
				a.setNumberRooms(rs.getInt("number_rooms"));
				a.setNumberBathroom(rs.getInt("number_bathroom"));
				a.setArea(rs.getDouble("area"));
				a.setParkingSpace(rs.getInt("parking"));
				a.setDescription(rs.getString("description"));
				a.setType(2);
				a.setCondominiumFee(rs.getDouble("condominium_fee"));
				if ((rs.getInt("duplex")) == 0) {
					a.setDuplex(false);
				} else {
					a.setDuplex(true);
				}
				ad.setId(rs.getInt("id_address"));
				ad.setAddress(rs.getString("address"));
				ad.setNeighborhood(rs.getString("neighborhood"));
				ad.setZipcode(rs.getString("zipcode"));
				ad.setNumber(rs.getInt("number"));
				ad.setComp(rs.getString("comp"));
				ad.setCity(rs.getString("city"));
				ad.setState(rs.getString("state"));

				a.setAddress(ad);

				return a;
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	@Override
	public List<Apartment> getList() {
		List<Apartment> list = new ArrayList<Apartment>();

		try {
			PreparedStatement stmt = connect.prepareStatement("select TB_PROPERTY.id_property, "
					+ "TB_PROPERTY.price_rental, TB_PROPERTY.number_rooms, TB_PROPERTY.number_bathroom, "
					+ "TB_PROPERTY.area, TB_PROPERTY.parking, TB_PROPERTY.description, TB_PROPERTY.cpf_tenant, "
					+ "TB_PROPERTY.type_property, TB_APARTMENT.condominium_fee, "
					+ "TB_APARTMENT.duplex, TB_ADDRESS.id_address, TB_ADDRESS.address, TB_ADDRESS.neighborhood, "
					+ "TB_ADDRESS.zipcode, TB_ADDRESS.number, TB_ADDRESS.comp, "
					+ "TB_ADDRESS.city, TB_ADDRESS.state from TB_PROPERTY, TB_ADDRESS, TB_APARTMENT "
					+ "where (TB_PROPERTY.id_property = TB_APARTMENT.id_property and "
					+ "    TB_PROPERTY.id_address = TB_ADDRESS.id_address);");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Apartment a = new Apartment();
				Address ad = new Address();
				a.setId(rs.getInt("id_property"));
				a.setType(2);
				a.setPriceRental(rs.getDouble("price_rental"));
				a.setNumberRooms(rs.getInt("number_rooms"));
				a.setNumberBathroom(rs.getInt("number_bathroom"));
				a.setArea(rs.getDouble("area"));
				a.setParkingSpace(rs.getInt("parking"));
				a.setDescription(rs.getString("description"));
				a.setCpfTenant(rs.getString("cpf_tenant"));
				a.setType(rs.getInt("type_property"));
				a.setCondominiumFee(rs.getDouble("condominium_fee"));
				if ((rs.getInt("duplex")) == 0) {
					a.setDuplex(false);
				} else {
					a.setDuplex(true);
				}
				ad.setId(rs.getInt("id_address"));
				ad.setAddress(rs.getString("address"));
				ad.setNeighborhood(rs.getString("neighborhood"));
				ad.setZipcode(rs.getString("zipcode"));
				ad.setNumber(rs.getInt("number"));
				ad.setComp(rs.getString("comp"));
				ad.setCity(rs.getString("city"));
				ad.setState(rs.getString("state"));

				a.setAddress(ad);

				list.add(a);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public boolean update(Apartment a, int id, int idaddress) {
		try {
			PreparedStatement stmt = connect.prepareStatement(
					"UPDATE TB_ADDRESS SET address=?, neighborhood=?, zipcode=?, number=?, comp=?, city=?, state=? "
							+ "WHERE id_address=?");
			stmt.setString(1, a.getAddress().getAddress());
			stmt.setString(2, a.getAddress().getNeighborhood());
			stmt.setString(3, a.getAddress().getZipcode());
			stmt.setInt(4, a.getAddress().getNumber());
			stmt.setString(5, a.getAddress().getComp());
			stmt.setString(6, a.getAddress().getCity());
			stmt.setString(7, a.getAddress().getState());

			stmt.setInt(8, idaddress);

			stmt.execute();

			stmt = connect.prepareStatement("UPDATE TB_PROPERTY SET price_rental=?, number_rooms=?,"
					+ "number_bathroom=?, area=?, parking=?, description=? WHERE id_property=?");
			stmt.setDouble(1, a.getPriceRental());
			stmt.setInt(2, a.getNumberRooms());
			stmt.setInt(3, a.getNumberBathroom());
			stmt.setDouble(4, a.getArea());
			stmt.setInt(5, a.getParkingSpace());
			stmt.setString(6, a.getDescription());

			stmt.setInt(7, id);

			stmt.execute();

			stmt = connect
					.prepareStatement("UPDATE TB_APARTMENT SET condominium_fee=?, duplex=? " + "WHERE id_property=?");
			stmt.setDouble(1, a.getCondominiumFee());
			if (a.isDuplex()) {
				stmt.setInt(2, 1);
			} else {
				stmt.setInt(2, 0);
			}

			stmt.setInt(3, id);

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
			PreparedStatement stmt = connect.prepareStatement("DELETE FROM TB_APARTMENT WHERE (id_property=?)");
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
		int nextId = 1;
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT ID = IDENT_CURRENT('TB_PROPERTY');");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				nextId = rs.getInt("ID");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return nextId;
	}

}
