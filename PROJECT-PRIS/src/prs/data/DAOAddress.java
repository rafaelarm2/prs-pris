package prs.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOAddress implements IRepositoryAddress {
	private Connection connect;
	
	public DAOAddress() throws SQLException {
		//CREATE A CONNECTION
		connect = ConnectionFactory.getConnection();
	}

	@Override
	public int getNextId() {
		int nextId = 1;
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT ID = IDENT_CURRENT('TB_ADDRESS');");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				nextId = rs.getInt("ID");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return nextId;
	}

	@Override
	public boolean delete(int id) {
		try {
			PreparedStatement stmt = connect
					.prepareStatement("DELETE FROM TB_ADDRESS WHERE id_address=?");
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
