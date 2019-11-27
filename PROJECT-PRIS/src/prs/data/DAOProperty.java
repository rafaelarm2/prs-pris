package prs.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOProperty implements IRepositoryProperty {
	//private IRepositoryAddress rAddress;
	private Connection connect;
	
	public DAOProperty() throws SQLException {
		//CREATE A CONNECTION
		connect = ConnectionFactory.getConnection();

	}
	@Override
	public int getType(int id) {
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT TB_PROPERTY.type_property FROM TB_PROPERTY"
					+ " WHERE (id_property=?);");

			stmt.setInt(1,id);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getInt("type_property");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int getIdAddress(int id) {
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT TB_PROPERTY.id_address FROM TB_PROPERTY"
					+ " WHERE (id_property=?);");

			stmt.setInt(1,id);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getInt("id_address");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public boolean delete(int id) {
		try {
			PreparedStatement stmt = connect
					.prepareStatement("DELETE FROM TB_PROPERTY WHERE (id_property=?)");
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
	public boolean rental(int id, String cpf) {
		try {
			
			PreparedStatement stmt = connect.prepareStatement( "UPDATE TB_PROPERTY SET cpf_tenant=?"
					+ " WHERE id_property=?");
			stmt.setString(1, cpf);
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
	public boolean rented(int id) {
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT TB_PROPERTY.cpf_tenant FROM TB_PROPERTY"
					+ " WHERE (id_property=?);");

			stmt.setInt(1,id);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("cpf_tenant") == null) {
					return false;
				} else {
					return true;
				}
			}
			stmt.close();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
